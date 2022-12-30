-- Таблица users имеет привязку к ролям
-- customers привязана к users, на ее основе будет наполняться заказ
-- В заказ будет передан id клиента, id продукта, вид доставки и, в случае доставки в пункт выдачи - id пункта.
-- Сначала создается запись в таблице order_ids затем вносятся записи в таблицу orders


CREATE TABLE "users" (
                         "id" bigserial   NOT NULL,
                         "username" varchar(255)   NOT NULL,
                         "password" varchar(255)   NOT NULL,
                         "customer_id" int   NOT NULL,
                         CONSTRAINT "pk_users" PRIMARY KEY (
                                                            "id"
                             )
);

CREATE TABLE "roles" (
                         "id" bigserial   NOT NULL,
                         "name" varchar(100)   NOT NULL,
                         CONSTRAINT "pk_roles" PRIMARY KEY (
                                                            "id"
                             )
);

CREATE TABLE "users_roles" (
                               "user_id" int   NOT NULL,
                               "role_id" int   NOT NULL
);

CREATE TABLE "products" (
                            "id" bigserial   NOT NULL,
                            "name" varchar(255)   NOT NULL,
                            "description" varchar(255)   NULL,
                            "cost" decimal   NOT NULL,
                            CONSTRAINT "pk_products" PRIMARY KEY (
                                                                  "id"
                                )
);

CREATE TABLE "order_items" (
                            "id" bigserial   NOT NULL,
                            "product_id" int  NOT NULL,
                            "quantity" int   NULL,
                            CONSTRAINT "pk_order_items" PRIMARY KEY (
                                                                  "id"
                                )
);

CREATE TABLE "customers" (
                             "id" bigserial   NOT NULL,
                             "first_name" varchar(255)   NULL,
                             "middle_name" varchar(255)   NULL,
                             "last_name" varchar(255)   NULL,
                             "email" varchar(150)   NOT NULL,
                             "country" varchar(100)   NULL,
                             "region" varchar(255)   NULL,
                             "district" varchar(255)   NULL,
                             "city" varchar(150)   NULL,
                             "address" varchar(255)   NULL,
                             "phone" varchar(15)   NULL,
                             CONSTRAINT "pk_customers" PRIMARY KEY (
                                                                    "id"
                                 )
);

CREATE TABLE "order_details" (
                                 "id" bigserial   NOT NULL,
                                 "customer_id" int   NOT NULL,
                                 "delivery_type_id" int   NOT NULL,
                                 "pickup_point_id" int   NULL,
                                 CONSTRAINT "pk_order_details" PRIMARY KEY (
                                                                    "id"
                                     )
);

CREATE TABLE "delivery_types" (
                                  "id" bigserial   NOT NULL,
                                  "title" varchar(255)   NOT NULL,
                                  CONSTRAINT "pk_delivery_types" PRIMARY KEY (
                                                                              "id"
                                      )
);

CREATE TABLE "pickup_points" (
                                  "id" bigserial   NOT NULL,
                                  "title" varchar(255)   NOT NULL,
                                  "email" varchar(150)   NOT NULL,
                                  "country" varchar(100)   NOT NULL,
                                  "region" varchar(255)   NOT NULL,
                                  "district" varchar(255)   NULL,
                                  "city" varchar(150)   NOT NULL,
                                  "address" varchar(255)   NOT NULL,
                                  "phone" varchar(15)   NOT NULL,
                                  CONSTRAINT "pk_pick_up_points" PRIMARY KEY (
                                                                              "id"
                                      )
);

CREATE TABLE "orders" (
                          "order_details_id" int   NOT NULL,
                          "order_item_id" int   NOT NULL
);

ALTER TABLE "orders" ADD CONSTRAINT "fk_orders_order_details_id" FOREIGN KEY("order_details_id")
    REFERENCES "order_details" ("id");

ALTER TABLE "orders" ADD CONSTRAINT "fk_orders_order_item_id" FOREIGN KEY("order_item_id")
    REFERENCES "order_items" ("id");

ALTER TABLE "users_roles" ADD CONSTRAINT "fk_users_roles_user_id" FOREIGN KEY("user_id")
    REFERENCES "users" ("id");

ALTER TABLE "users_roles" ADD CONSTRAINT "fk_users_roles_role_id" FOREIGN KEY("role_id")
    REFERENCES "roles" ("id");

ALTER TABLE "users" ADD CONSTRAINT "fk_users_customer_id" FOREIGN KEY("customer_id")
    REFERENCES "customers" ("id");

ALTER TABLE "order_details" ADD CONSTRAINT "fk_order_details_customer_id" FOREIGN KEY("customer_id")
    REFERENCES "customers" ("id");

ALTER TABLE "order_details" ADD CONSTRAINT "fk_order_details_delivery_type_id" FOREIGN KEY("delivery_type_id")
    REFERENCES "delivery_types" ("id");

ALTER TABLE "order_details" ADD CONSTRAINT "fk_order_details_pickup_point_id" FOREIGN KEY("pickup_point_id")
    REFERENCES "pickup_points" ("id");

