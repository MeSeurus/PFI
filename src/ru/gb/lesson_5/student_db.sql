CREATE TABLE IF NOT EXISTS public.student
(
    id bigserial NOT NULL DEFAULT nextval('student_id_seq'::regclass),
    name character varying(255) COLLATE pg_catalog."default",
    mark double precision,
    CONSTRAINT student_pkey PRIMARY KEY (id)
)