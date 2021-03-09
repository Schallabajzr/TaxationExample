create table taxbaseentity
(
    id            bigint           not null
        constraint taxbaseentity_pkey
            primary key,
    datecreated   timestamp,
    dateupdated   timestamp,
    taxfixedrate  boolean          not null,
    taxonwinnings boolean          not null,
    value         double precision not null
);

alter table taxbaseentity
    owner to quarkus;

create table traderentity
(
    id          bigint not null
        constraint traderentity_pkey
            primary key,
    datecreated timestamp,
    dateupdated timestamp,
    uuid        varchar(255),
    taxbase_id  bigint
        constraint fk2dgqyrao0qk3krweqypxswf22
            references taxbaseentity
);

alter table traderentity
    owner to quarkus;

create sequence hibernate_sequence;

alter sequence hibernate_sequence owner to quarkus;

INSERT INTO public.taxbaseentity (id, datecreated, dateupdated, taxfixedrate, taxonwinnings, value)
VALUES (2, '2021-03-08 22:30:28.229731', null, false, false, 0.1);
INSERT INTO public.taxbaseentity (id, datecreated, dateupdated, taxfixedrate, taxonwinnings, value)
VALUES (4, '2021-03-08 22:30:28.240730', null, true, false, 2);
INSERT INTO public.taxbaseentity (id, datecreated, dateupdated, taxfixedrate, taxonwinnings, value)
VALUES (6, '2021-03-08 22:30:28.247730', null, false, true, 0.1);
INSERT INTO public.taxbaseentity (id, datecreated, dateupdated, taxfixedrate, taxonwinnings, value)
VALUES (8, '2021-03-08 22:30:28.252751', null, true, true, 2);

INSERT INTO public.traderentity (id, datecreated, dateupdated, uuid, taxbase_id)
VALUES (1, '2021-03-08 22:30:28.183765', null, '6a0f7248-9158-4fab-b01f-2e360d090183', 2);
INSERT INTO public.traderentity (id, datecreated, dateupdated, uuid, taxbase_id)
VALUES (3, '2021-03-08 22:30:28.237731', null, 'd7b10a07-1b6d-4a81-8148-b54f95dd4f95', 4);
INSERT INTO public.traderentity (id, datecreated, dateupdated, uuid, taxbase_id)
VALUES (5, '2021-03-08 22:30:28.243726', null, '0b7b3535-74ac-4476-9ea3-70bb1f660b1d', 6);
INSERT INTO public.traderentity (id, datecreated, dateupdated, uuid, taxbase_id)
VALUES (7, '2021-03-08 22:30:28.250730', null, 'a756382a-6734-4746-bcbe-647f8be64343', 8);


