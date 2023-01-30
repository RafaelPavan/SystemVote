CREATE TABLE voting_session(
    id bigserial,
    fk_agenda bigint,
    date_voting_opening date,
    time_voting_opening time,
    time_voting_closing time,
    time_voting bigint,
    voting_status varchar,
    PRIMARY KEY (id),
    FOREIGN KEY(fk_agenda) REFERENCES agenda (id)

)