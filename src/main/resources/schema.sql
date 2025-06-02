/*
DROP TABLE "TB_REPLY_LIKE";
DROP TABLE "TB_BOARD_LIKE";
DROP TABLE "TB_IMAGE";
DROP TABLE "TB_REPLY";
DROP TABLE "TB_MENU_DETAIL";
DROP TABLE "TB_DAILY_MENU";
DROP TABLE "TB_EXERCISE_DETAIL";
DROP TABLE "TB_DAILY_EXERCISE";
DROP TABLE "TB_ALARM";
DROP TABLE "TB_FOLLOW";
DROP TABLE "TB_TODO";
DROP TABLE "TB_BOARD";
DROP TABLE "TB_BLOG";
DROP TABLE "TB_WITHDRAW";
DROP TABLE "TB_MEMBER_DETAIL";
DROP TABLE "TB_MENU";
DROP TABLE "TB_EXERCISE";
DROP TABLE "TB_MEMBER";

DROP SEQUENCE SEQ_BOARD_ID;
DROP SEQUENCE SEQ_REPLY_ID;
DROP SEQUENCE SEQ_MEMBER_ID;
DROP SEQUENCE SEQ_NOTICE_ID;
DROP SEQUENCE SEQ_TODO_ID;
DROP SEQUENCE SEQ_DAILY_MENU_ID;
DROP SEQUENCE SEQ_MENU_ID;
DROP SEQUENCE SEQ_DAILY_EXERCISE_ID;
DROP SEQUENCE SEQ_EXERCISE_ID;
*/

CREATE TABLE "TB_MENU"
(
    "MENU_ID"       NUMBER                                                           NOT NULL,
    "NAME"          VARCHAR2(100)                                                    NOT NULL,
    "CALORIE"       NUMBER                                                           NOT NULL,
    "CARBOHYDRATE"  NUMBER                                                           NOT NULL,
    "PROTEIN"       NUMBER                                                           NOT NULL,
    "MENU_CATEGORY" VARCHAR2(30) CHECK (MENU_CATEGORY IN ('RICE', 'SIDES', 'SOUPS')) NOT NULL
);

CREATE TABLE "TB_ALARM"
(
    "NOTICE_ID"          NUMBER                                                                NOT NULL,
    "NOTICE_CONTENT"     VARCHAR2(100)                                                         NOT NULL,
    "CATEGORY"           VARCHAR(30) CHECK ("CATEGORY" IN ('REPLY', 'TODO', 'FOLLOW', 'LIKE')) NULL,
    "CREATED_AT"         DATE DEFAULT SYSDATE                                                  NOT NULL,
    "RECEIVER_MEMBER_ID" NUMBER                                                                NOT NULL,
    "SENDER_MEMBER_ID"   NUMBER                                                                NOT NULL
);

CREATE TABLE "TB_MEMBER_DETAIL"
(
    "MEMBER_ID" NUMBER NOT NULL,
    "HEIGHT"    NUMBER NOT NULL,
    "WEIGHT"    NUMBER NOT NULL
);

CREATE TABLE "TB_MENU_DETAIL"
(
    "DAILY_MENU_ID" NUMBER NOT NULL,
    "MENU_ID"       NUMBER NOT NULL
);

CREATE TABLE "TB_MEMBER"
(
    "MEMBER_ID"  NUMBER                               NOT NULL,
    "EMAIL"      VARCHAR2(255) UNIQUE                 NOT NULL,
    "PASSWORD"   VARCHAR2(255)                        NOT NULL,
    "NICKNAME"   VARCHAR2(50) UNIQUE                  NOT NULL,
    "GENDER"     CHAR(1) CHECK (GENDER IN ('M', 'F')) NOT NULL,
    "BIRTH_AT"   DATE                                 NOT NULL,
    "PHONE"      VARCHAR2(13) UNIQUE                  NOT NULL,
    "CREATED_AT" DATE    DEFAULT SYSDATE              NOT NULL,
    "STATUS"     CHAR(1) DEFAULT 'Y'                  NOT NULL
);

CREATE TABLE "TB_BLOG"
(
    "MEMBER_ID" NUMBER                             NOT NULL,
    "INTRODUCE" VARCHAR2(255) DEFAULT '소개글이 없습니다.' NULL,
    "GRADE"     NUMBER        DEFAULT 0            NOT NULL
);

CREATE TABLE "TB_IMAGE"
(
    "ORIGIN_NAME" VARCHAR2(255) NOT NULL,
    "CHANGE_NAME" VARCHAR2(255) NOT NULL,
    "BOARD_ID"    NUMBER        NOT NULL
);

CREATE TABLE "TB_DAILY_MENU"
(
    "DAILY_MENU_ID" NUMBER NOT NULL,
    "TODO_ID"       NUMBER NOT NULL,
    "DAY_NO"        NUMBER NOT NULL
);

CREATE TABLE "TB_REPLY_LIKE"
(
    "MEMBER_ID" NUMBER NOT NULL,
    "REPLY_ID"  NUMBER NOT NULL
);

CREATE TABLE "TB_DAILY_EXERCISE"
(
    "DAILY_EXERCISE_ID" NUMBER NOT NULL,
    "TODO_ID"           NUMBER NOT NULL,
    "DAY_NO"            NUMBER NOT NULL
);

CREATE TABLE "TB_WITHDRAW"
(
    "MEMBER_ID"     NUMBER               NOT NULL,
    "REASON_SELECT" VARCHAR2(255)        NULL,
    "REASON_INPUT"  VARCHAR2(3000)       NULL,
    "WITHDRAW_AT"   DATE DEFAULT SYSDATE NOT NULL
);

CREATE TABLE "TB_EXERCISE"
(
    "EXERCISE_ID"       NUMBER                                                                         NOT NULL,
    "NAME"              VARCHAR2(100)                                                                  NOT NULL,
    "MET"               NUMBER                                                                         NOT NULL,
    "EXERCISE_CATEGORY" VARCHAR2(50) CHECK (EXERCISE_CATEGORY IN ('UPPER', 'LOWER', 'CARDIO', 'LIFE')) NOT NULL
);

CREATE TABLE "TB_REPLY"
(
    "REPLY_ID"        NUMBER               NOT NULL,
    "BOARD_ID"        NUMBER               NOT NULL,
    "MEMBER_ID"       NUMBER               NOT NULL,
    "REPLY_CONTENT"   VARCHAR2(1500)       NOT NULL,
    "ANSWER_REPLY_ID" NUMBER               NULL,
    "REPLIED_AT"      DATE DEFAULT SYSDATE NOT NULL
);

CREATE TABLE "TB_FOLLOW"
(
    "FOLLOWED_MEMBER_ID" NUMBER NOT NULL,
    "CLICKED_MEMBER_ID"  NUMBER NOT NULL
);

CREATE TABLE "TB_BOARD"
(
    "BOARD_ID"      NUMBER                                                                                    NOT NULL,
    "TITLE"         VARCHAR2(150)                                                                             NOT NULL,
    "BOARD_CONTENT" VARCHAR2(1500)                                                                            NULL,
    "CATEGORY"      VARCHAR2(20) DEFAULT 'ALL' CHECK ("CATEGORY" IN ('ALL', 'DIET', 'STRENGTH', 'ENDURANCE')) NOT NULL,
    "CREATED_AT"    DATE         DEFAULT SYSDATE                                                              NOT NULL,
    "MEMBER_ID"     NUMBER                                                                                    NOT NULL
);

CREATE TABLE "TB_TODO"
(
    "TODO_ID"     NUMBER                                                                             NOT NULL,
    "MEMBER_ID"   NUMBER                                                                             NOT NULL,
    "STARTED_AT"  DATE                                                                               NOT NULL,
    "ENDED_AT"    DATE                                                                               NOT NULL,
    "PURPOSE"     VARCHAR2(30) CHECK ("PURPOSE" IN ('ENDURANCE', 'STRENGTH', 'MAINTENANCE', 'DIET')) NULL,
    "CATEGORY"    VARCHAR2(8)                                                                        NOT NULL,
    "DAY_KCAL"    NUMBER                                                                             NULL,
    "DAY_REPEAT"  NUMBER                                                                             NULL,
    "TDEE"        NUMBER                                                                             NULL,
    "GOAL_WEIGHT" NUMBER                                                                             NULL
);

CREATE TABLE "TB_EXERCISE_DETAIL"
(
    "DAILY_EXERCISE_ID" NUMBER NOT NULL,
    "EXERCISE_ID"       NUMBER NOT NULL
);

CREATE TABLE "TB_BOARD_LIKE"
(
    "MEMBER_ID" NUMBER NOT NULL,
    "BOARD_ID"  NUMBER NOT NULL
);

--  SEQ --

CREATE SEQUENCE SEQ_BOARD_ID NOCACHE;
CREATE SEQUENCE SEQ_REPLY_ID NOCACHE;
CREATE SEQUENCE SEQ_MEMBER_ID NOCACHE;
CREATE SEQUENCE SEQ_NOTICE_ID NOCACHE;
CREATE SEQUENCE SEQ_TODO_ID NOCACHE;
CREATE SEQUENCE SEQ_DAILY_MENU_ID NOCACHE;
CREATE SEQUENCE SEQ_MENU_ID NOCACHE;
CREATE SEQUENCE SEQ_DAILY_EXERCISE_ID NOCACHE;
CREATE SEQUENCE SEQ_EXERCISE_ID NOCACHE;


ALTER TABLE "TB_MENU"
    ADD CONSTRAINT "PK_TB_MENU" PRIMARY KEY (
                                             "MENU_ID"
        );

ALTER TABLE "TB_ALARM"
    ADD CONSTRAINT "PK_TB_ALARM" PRIMARY KEY (
                                              "NOTICE_ID"
        );

ALTER TABLE "TB_MEMBER_DETAIL"
    ADD CONSTRAINT "PK_TB_MEMBER_DETAIL" PRIMARY KEY (
                                                      "MEMBER_ID"
        );

ALTER TABLE "TB_MEMBER"
    ADD CONSTRAINT "PK_TB_MEMBER" PRIMARY KEY (
                                               "MEMBER_ID"
        );

ALTER TABLE "TB_BLOG"
    ADD CONSTRAINT "PK_TB_BLOG" PRIMARY KEY (
                                             "MEMBER_ID"
        );

ALTER TABLE "TB_DAILY_MENU"
    ADD CONSTRAINT "PK_TB_DAILY_MENU" PRIMARY KEY (
                                                   "DAILY_MENU_ID"
        );

ALTER TABLE "TB_DAILY_EXERCISE"
    ADD CONSTRAINT "PK_TB_DAILY_EXERCISE" PRIMARY KEY (
                                                       "DAILY_EXERCISE_ID"
        );

ALTER TABLE "TB_WITHDRAW"
    ADD CONSTRAINT "PK_TB_WITHDRAW" PRIMARY KEY (
                                                 "MEMBER_ID"
        );

ALTER TABLE "TB_EXERCISE"
    ADD CONSTRAINT "PK_TB_EXERCISE" PRIMARY KEY (
                                                 "EXERCISE_ID"
        );

ALTER TABLE "TB_REPLY"
    ADD CONSTRAINT "PK_TB_REPLY" PRIMARY KEY (
                                              "REPLY_ID"
        );

ALTER TABLE "TB_BOARD"
    ADD CONSTRAINT "PK_TB_BOARD" PRIMARY KEY (
                                              "BOARD_ID"
        );

ALTER TABLE "TB_TODO"
    ADD CONSTRAINT "PK_TB_TODO" PRIMARY KEY (
                                             "TODO_ID"
        );

ALTER TABLE "TB_MEMBER_DETAIL"
    ADD CONSTRAINT "FK_TB_MEMBER_TO_TB_MEMBER_DETAIL_1" FOREIGN KEY (
                                                                     "MEMBER_ID"
        )
        REFERENCES "TB_MEMBER" (
                                "MEMBER_ID"
            );

ALTER TABLE "TB_BLOG"
    ADD CONSTRAINT "FK_TB_MEMBER_TO_TB_BLOG_1" FOREIGN KEY (
                                                            "MEMBER_ID"
        )
        REFERENCES "TB_MEMBER" (
                                "MEMBER_ID"
            );

ALTER TABLE "TB_WITHDRAW"
    ADD CONSTRAINT "FK_TB_MEMBER_TO_TB_WITHDRAW_1" FOREIGN KEY (
                                                                "MEMBER_ID"
        )
        REFERENCES "TB_MEMBER" (
                                "MEMBER_ID"
            );

-- FK 추가 작성 --
/*
---- ON DELETE CASCADE 사용된 FK컬럼들 ----
TB_IMAGE, TB_REPLY(BOARD_ID), TB_BOARD_LIKE(BOARD_ID), TB_REPLY_LIKE(REPLY_ID),
TB_FOLLOW(FOLLOWED·CLICKED_MEMBER_ID), TB_TODO, TB_ALARM(RECEIVER·SENDER_MEMBER_ID), TB_DAILY_MENU,
TB_MENU_DETAIL(DAILY_MENU_ID), TB_DAILY_EXERCISE, TB_EXERCISE_DETAIL(DAILY_EXERCISE_ID)
*/

ALTER TABLE "TB_BOARD"
    ADD CONSTRAINT "FK_TB_MEMBER_TO_TB_BOARD_1" FOREIGN KEY (
                                                             "MEMBER_ID"
        )
        REFERENCES "TB_MEMBER" (
                                "MEMBER_ID"
            );

ALTER TABLE "TB_IMAGE"
    ADD CONSTRAINT "FK_TB_BOARD_TO_TB_IMAGE_1" FOREIGN KEY (
                                                            "BOARD_ID"
        )
        REFERENCES "TB_BOARD" (
                               "BOARD_ID"
            )
            ON DELETE CASCADE;

ALTER TABLE "TB_REPLY"
    ADD CONSTRAINT "FK_TB_BOARD_TO_TB_REPLY_1" FOREIGN KEY (
                                                            "BOARD_ID"
        )
        REFERENCES "TB_BOARD" (
                               "BOARD_ID"
            )
            ON DELETE CASCADE;

ALTER TABLE "TB_REPLY"
    ADD CONSTRAINT "FK_TB_MEMBER_TO_TB_REPLY_2" FOREIGN KEY (
                                                             "MEMBER_ID"
        )
        REFERENCES "TB_MEMBER" (
                                "MEMBER_ID"
            );

ALTER TABLE "TB_BOARD_LIKE"
    ADD CONSTRAINT "FK_TB_BOARD_TO_TB_BOARD_LIKE_1" FOREIGN KEY (
                                                                 "BOARD_ID"
        )
        REFERENCES "TB_BOARD" (
                               "BOARD_ID"
            )
            ON DELETE CASCADE;

ALTER TABLE "TB_BOARD_LIKE"
    ADD CONSTRAINT "FK_TB_MEMBER_TO_TB_BOARD_LIKE_1" FOREIGN KEY (
                                                                  "MEMBER_ID"
        )
        REFERENCES "TB_MEMBER" (
                                "MEMBER_ID"
            );

ALTER TABLE "TB_REPLY_LIKE"
    ADD CONSTRAINT "FK_TB_MEMBER_TO_TB_REPLY_LIKE_1" FOREIGN KEY (
                                                                  "MEMBER_ID"
        )
        REFERENCES "TB_MEMBER" (
                                "MEMBER_ID"
            );

ALTER TABLE "TB_REPLY_LIKE"
    ADD CONSTRAINT "FK_TB_REPLY_TO_TB_REPLY_LIKE_1" FOREIGN KEY (
                                                                 "REPLY_ID"
        )
        REFERENCES "TB_REPLY" (
                               "REPLY_ID"
            )
            ON DELETE CASCADE;

ALTER TABLE "TB_FOLLOW"
    ADD CONSTRAINT "FK_TB_MEMBER_TO_TB_FOLLOW_2" FOREIGN KEY (
                                                              "CLICKED_MEMBER_ID"
        )
        REFERENCES "TB_MEMBER" (
                                "MEMBER_ID"
            )
            ON DELETE CASCADE;

ALTER TABLE "TB_FOLLOW"
    ADD CONSTRAINT "FK_TB_MEMBER_TO_TB_FOLLOW_1" FOREIGN KEY (
                                                              "FOLLOWED_MEMBER_ID"
        )
        REFERENCES "TB_MEMBER" (
                                "MEMBER_ID"
            )
            ON DELETE CASCADE;

ALTER TABLE "TB_TODO"
    ADD CONSTRAINT "FK_TB_MEMBER_TO_TB_TODO_1" FOREIGN KEY (
                                                            "MEMBER_ID"
        )
        REFERENCES "TB_MEMBER" (
                                "MEMBER_ID"
            )
            ON DELETE CASCADE;

ALTER TABLE "TB_ALARM"
    ADD CONSTRAINT "FK_TB_MEMBER_TO_TB_ALARM_1" FOREIGN KEY (
                                                             "RECEIVER_MEMBER_ID"
        )
        REFERENCES "TB_MEMBER" (
                                "MEMBER_ID"
            )
            ON DELETE CASCADE;

ALTER TABLE "TB_ALARM"
    ADD CONSTRAINT "FK_TB_MEMBER_TO_TB_ALARM_2" FOREIGN KEY (
                                                             "SENDER_MEMBER_ID"
        )
        REFERENCES "TB_MEMBER" (
                                "MEMBER_ID"
            )
            ON DELETE CASCADE;

ALTER TABLE "TB_DAILY_MENU"
    ADD CONSTRAINT "FK_TB_TODO_TO_TB_DAILY_MENU_1" FOREIGN KEY (
                                                                "TODO_ID"
        )
        REFERENCES "TB_TODO" (
                              "TODO_ID"
            )
            ON DELETE CASCADE;

ALTER TABLE "TB_MENU_DETAIL"
    ADD CONSTRAINT "FK_TB_DAILY_MENU_TO_TB_MENU_DETAIL_1" FOREIGN KEY (
                                                                       "DAILY_MENU_ID"
        )
        REFERENCES "TB_DAILY_MENU" (
                                    "DAILY_MENU_ID"
            )
            ON DELETE CASCADE;

ALTER TABLE "TB_MENU_DETAIL"
    ADD CONSTRAINT "FK_TB_MENU_TO_TB_MENU_DETAIL_1" FOREIGN KEY (
                                                                 "MENU_ID"
        )
        REFERENCES "TB_MENU" (
                              "MENU_ID"
            );

ALTER TABLE "TB_DAILY_EXERCISE"
    ADD CONSTRAINT "FK_TB_TODO_TO_TB_DAILY_EXERCISE_1" FOREIGN KEY (
                                                                    "TODO_ID"
        )
        REFERENCES "TB_TODO" (
                              "TODO_ID"
            )
            ON DELETE CASCADE;

ALTER TABLE "TB_EXERCISE_DETAIL"
    ADD CONSTRAINT "FK_TB_DAILY_EXERCISE_TO_TB_EXERCISE_DETAIL_1" FOREIGN KEY (
                                                                               "DAILY_EXERCISE_ID"
        )
        REFERENCES "TB_DAILY_EXERCISE" (
                                        "DAILY_EXERCISE_ID"
            )
            ON DELETE CASCADE;

ALTER TABLE "TB_EXERCISE_DETAIL"
    ADD CONSTRAINT "FK_TB_EXERCISE_TO_TB_EXERCISE_DETAIL_1" FOREIGN KEY (
                                                                         "EXERCISE_ID"
        )
        REFERENCES "TB_EXERCISE" (
                                  "EXERCISE_ID"
            );
