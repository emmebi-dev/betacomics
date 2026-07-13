
    set client_min_messages = WARNING;

    alter table if exists action_figures 
       drop constraint if exists FK2ghymkb7y2qrn220muug2qyim;

    alter table if exists board_games 
       drop constraint if exists FKbl9715l6k58gjfxkjwv3xkqs2;

    alter table if exists cart_items 
       drop constraint if exists FKpcttvuq4mxppo8sxggjtn5i2c;

    alter table if exists cart_items 
       drop constraint if exists FK1re40cjegsfvw58xrkdp6bac6;

    alter table if exists carts 
       drop constraint if exists FKb5o626f86h46m4s7ms6ginnop;

    alter table if exists comics 
       drop constraint if exists FK3eylnxym8v2hqrc9dpkykwn36;

    alter table if exists order_item 
       drop constraint if exists FKt4dc2r9nbvbujrljv3e23iibt;

    alter table if exists order_item 
       drop constraint if exists FKc5uhmwioq5kscilyuchp4w49o;

    alter table if exists orders 
       drop constraint if exists FK32ql8ubntj5uh44ph9659tiih;

    drop table if exists action_figures cascade;

    drop table if exists board_games cascade;

    drop table if exists cart_items cascade;

    drop table if exists carts cascade;

    drop table if exists comics cascade;

    drop table if exists error_message cascade;

    drop table if exists order_item cascade;

    drop table if exists orders cascade;

    drop table if exists products cascade;

    drop table if exists users cascade;
