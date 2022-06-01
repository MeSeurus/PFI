CREATE TABLE films
(
    id           bigserial primary key,
    title        varchar(255) not null,
    duration     int not null
);

CREATE TABLE sessions
(
    id           bigserial primary key,
    film_id      int not null references films (id),
    ticket_price numeric(8, 2) not null,
    start_time   timestamp(0) not null
);

CREATE TABLE tickets
(
    id           bigserial primary key,
    session_id   bigint not null references sessions (id)
);

INSERT INTO films (title, duration)
VALUES ('Star Wars Episode I - The Phantom Menace', 136),
       ('Star Wars Episode II - Attack of the Clones', 142),
       ('Star Wars Episode III - Revenge of the Sith', 140),
       ('Star Wars Episode IV - A New Hope', 121),
       ('Star Wars Episode V - The Empire Strikes Back', 124);

INSERT INTO sessions (film_id, ticket_price, start_time)
VALUES (1, 250.00, '2022-05-24 10:30:00'),
       (2, 250.00, '2022-05-24 11:30:00'),
       (3, 300.00, '2022-05-24 12:00:00'),
       (4, 450.00, '2022-05-24 21:00:00'),
       (5, 150.00, '2022-05-24 09:15:00'),
       (1, 300.00, '2022-05-24 16:00:00'),
       (3, 450.00, '2022-05-24 22:15:00'),
       (5, 350.00, '2022-05-24 18:30:00');

INSERT INTO tickets (session_id)
VALUES (1),(1),(1),(1),(1),
       (2),(2),(2),(2),
       (3),(3),(3),(3),(3),
       (4),(4),(4),(4),(4),(4),(4),(4),
       (5),(5),(5),
       (6),(6),(6),(6),
       (7),(7),(7),(7),(7),(7),(7),
       (8),(8),(8),(8),(8),(8);

-- Сделать запросы, считающие и выводящие в понятном виде:
-- ошибки в расписании (фильмы накладываются друг на друга), отсортированные по возрастанию времени.
-- Выводить надо колонки «фильм 1», «время начала», «длительность», «фильм 2», «время начала», «длительность»;

WITH first AS (SELECT f.title,
                      s.start_time                            AS first_starts,
                      f.duration                              AS first_length,
                      lead(f.title) OVER (ORDER BY s.id),
                      lead(s.start_time) OVER (ORDER BY s.id) AS second_starts,
                      lead(f.duration) OVER (ORDER BY s.id)   AS second_length
               FROM films f
                        LEFT JOIN sessions s ON f.id = s.film_id)
SELECT *
FROM first
WHERE (SELECT (first_starts, first_starts + make_interval(mins => first_length))
                  overlaps
              (second_starts, second_starts + make_interval(mins => second_length)));

-- перерывы 30 минут и более между фильмами — выводить по уменьшению длительности перерыва.
-- Колонки «фильм 1», «время начала», «длительность», «время начала второго фильма», «длительность перерыва»;

WITH second AS (SELECT f.title,
                       s.start_time,
                       f.duration,
                       lead(s.start_time) OVER (ORDER BY s.id),
                       age(lead(s.start_time) OVER (ORDER BY s.id),
                           (SELECT s.start_time + make_interval(mins => f.duration))) AS overlap
                FROM films f
                         LEFT JOIN sessions s ON f.id = s.film_id
                ORDER BY overlap DESC)
SELECT *
FROM second
WHERE overlap >= interval '00:30:00';

-- список фильмов, для каждого — с указанием общего числа посетителей за все время,
-- среднего числа зрителей за сеанс и общей суммы сборов по каждому фильму (отсортировать по убыванию прибыли).
-- Внизу таблицы должна быть строчка «итого», содержащая данные по всем фильмам сразу;

WITH third as (SELECT f.title, count(t.id) AS sum,
                      (SELECT avg(guests)
                       FROM (SELECT count(t.id) AS guests
                             FROM tickets t
                                      LEFT JOIN sessions s ON s.id = t.session_id
                             WHERE s.film_id = f.id
                             GROUP BY s.id)),
                      sum(s.ticket_price) AS sold
               FROM films f
                        LEFT JOIN sessions s ON f.id = s.film_id
                        LEFT JOIN tickets t ON s.id = t.session_id
               GROUP BY f.id
               ORDER BY sold DESC)
        (SELECT * FROM third)
UNION ALL
(SELECT 'Total: ',
        sum(third.sum),
        round(sum(third.sum) / (SELECT count(s.id) FROM sessions s)),
        sum(third.sold)
 FROM third);

-- число посетителей и кассовые сборы, сгруппированные по времени начала фильма:
-- с 9 до 15, с 15 до 18, с 18 до 21, с 21 до 00:00 (сколько посетителей пришло с 9 до 15 часов и т.д.).

SELECT CASE
           WHEN start_time BETWEEN '2022-05-24 09:00:00' AND '2022-05-24 15:00:00' THEN '9 - 15'
           WHEN start_time BETWEEN '2022-05-24 15:00:00' AND '2022-05-24 18:00:00' THEN '15 - 18'
           WHEN start_time BETWEEN '2022-05-24 18:00:00' AND '2022-05-24 21:00:00' THEN '18 - 21'
           WHEN start_time BETWEEN '2022-05-24 21:00:00' AND '2022-05-24 00:00:00' THEN '21 - 00'
           END             AS intervals,
       count(t.id)         AS visitors,
       sum(s.ticket_price) AS sold
FROM sessions s
         LEFT JOIN tickets t ON t.session_id = s.id
GROUP BY intervals;