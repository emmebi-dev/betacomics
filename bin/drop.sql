
    set client_min_messages = WARNING;

    alter table if exists action_figures 
       drop constraint if exists FK2ghymkb7y2qrn220muug2qyim;

    alter table if exists board_games 
       drop constraint if exists FKbl9715l6k58gjfxkjwv3xkqs2;

    alter table if exists comics 
       drop constraint if exists FK3eylnxym8v2hqrc9dpkykwn36;

    drop table if exists action_figures cascade;

    drop table if exists board_games cascade;

    drop table if exists comics cascade;

    drop table if exists error_message cascade;

    drop table if exists products cascade;
