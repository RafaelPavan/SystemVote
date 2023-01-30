CREATE TABLE votes(
    id bigserial,
    fk_associate bigint,
    fk_agenda bigint,
    vote varchar,
    vote_time time,
    PRIMARY KEY(id),
    FOREIGN KEY (fk_associate) REFERENCES associate(id),
    FOREIGN KEY (fk_agenda) REFERENCES agenda(id)
)