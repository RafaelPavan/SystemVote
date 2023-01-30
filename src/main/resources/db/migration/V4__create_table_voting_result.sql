CREATE TABLE voting_result(
    id bigserial,
    fk_agenda bigint,
    votes_quantity bigint,
    pros_votes int,
    cons_votes int,
    final_result varchar,
    PRIMARY KEY (id),
    FOREIGN KEY (fk_agenda) REFERENCES agenda(id)

)