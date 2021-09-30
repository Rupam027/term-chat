CREATE TABLE "chatters" (
  "chatter_id" SERIAL PRIMARY KEY,
  "name" TEXT NOT NULL,
  "password" TEXT NOT NULL,
  "session_id" TEXT 
);

CREATE TABLE "room" (
  "id" SERIAL PRIMARY KEY,
  "name" TEXT NOT NULL , 
  "room_identifier" TEXT NOT NULL ,
  "created_on" TEXT NOT NULL,
  "created_by" TEXT NOT NULL
);

CREATE TABLE "chat" (
  "id" SERIAL PRIMARY KEY,
  "chatters" INTEGER NOT NULL,
  "message" TEXT NOT NULL,
  "room" INTEGER NOT NULL
);

CREATE INDEX "idx_chat__chatters" ON "chat" ("chatters");

CREATE INDEX "idx_chat__room" ON "chat" ("room");

ALTER TABLE "chat" ADD CONSTRAINT "fk_chat__chatters" FOREIGN KEY ("chatters") REFERENCES "chatters" ("chatter_id") ON DELETE CASCADE;

ALTER TABLE "chat" ADD CONSTRAINT "fk_chat__room" FOREIGN KEY ("room") REFERENCES "room" ("id") ON DELETE CASCADE;

CREATE TABLE "room_chatters" (
  "user_chatter_id" INTEGER NOT NULL,
  "roommodel_id" INTEGER NOT NULL,
  PRIMARY KEY ("user_chatter_id", "roommodel_id")
);

CREATE INDEX "idx_chatters_room" ON "room_chatters" ("roommodel_id");

ALTER TABLE "room_chatters" ADD CONSTRAINT "fk_chatters_room__chatters" FOREIGN KEY ("user_chatter_id") REFERENCES "chatters" ("chatter_id");

ALTER TABLE "room_chatters" ADD CONSTRAINT "fk_chatters_room__room" FOREIGN KEY ("roommodel_id") REFERENCES "room" ("id");

CREATE TABLE "seen" (
  "id" SERIAL PRIMARY KEY,
  "chatters" INTEGER NOT NULL,
  "chat" INTEGER NOT NULL
);

CREATE INDEX "idx_seen__chat" ON "seen" ("chat");

CREATE INDEX "idx_seen__chatters" ON "seen" ("chatters");

ALTER TABLE "seen" ADD CONSTRAINT "fk_seen__chat" FOREIGN KEY ("chat") REFERENCES "chat" ("id") ON DELETE CASCADE;

ALTER TABLE "seen" ADD CONSTRAINT "fk_seen__chatters" FOREIGN KEY ("chatters") REFERENCES "chatters" ("chatter_id") ON DELETE CASCADE