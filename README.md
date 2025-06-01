# spring-ai-bedrock-project



docker run -d \
--name my-postgres \
-e POSTGRES_USER=postgres \
-e POSTGRES_PASSWORD=postgres \
-e POSTGRES_DB=bedrockdb \
-p 5432:5432 \
ankane/pgvector


create db bedrockdb

CREATE TABLE dog (
id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
"name" VARCHAR,
description VARCHAR,
owner VARCHAR,
dob VARCHAR
);




psql -U postgres
CREATE EXTENSION IF NOT EXISTS vector;
SELECT extversion FROM pg_extension WHERE extname = 'vector';



#CREATE USER bedrockuser WITH PASSWORD 'bedrockpassword';

INSERT INTO public.dog ("name", description, dob, owner) VALUES ('Buddy', 'Friendly golden retriever', '2018-05-12', 'John');
INSERT INTO public.dog ("name", description, dob, owner) VALUES ('Bella', 'Playful and smart', '2019-07-30', 'Emma');
INSERT INTO public.dog ("name", description, dob, owner) VALUES ('Charlie', 'Loves long walks', '2020-01-15', 'Michael');
INSERT INTO public.dog ("name", description, dob, owner) VALUES ('Lucy', 'Very affectionate', '2017-09-23', 'Sophia');
INSERT INTO public.dog ("name", description, dob, owner) VALUES ('Max', 'Energetic and loyal', '2021-03-03', 'David');
INSERT INTO public.dog ("name", description, dob, owner) VALUES ('Daisy', 'Gentle and sweet', '2016-11-18', 'Olivia');
INSERT INTO public.dog ("name", description, dob, owner) VALUES ('Rocky', 'Strong and silent', '2015-02-25', 'James');
INSERT INTO public.dog ("name", description, dob, owner) VALUES ('Lola', 'Small but brave', '2022-06-10', 'Isabella');
INSERT INTO public.dog ("name", description, dob, owner) VALUES ('Toby', 'Loves treats', '2019-12-01', 'William');
INSERT INTO public.dog ("name", description, dob, owner) VALUES ('Molly', 'Cuddly companion', '2020-10-14', 'Mia');
INSERT INTO public.dog ("name", description, dob, owner) VALUES ('Bailey', 'Always curious', '2018-08-05', 'Ethan');
INSERT INTO public.dog ("name", description, dob, owner) VALUES ('Maggie', 'Great with kids', '2017-03-28', 'Charlotte');
INSERT INTO public.dog ("name", description, dob, owner) VALUES ('Jack', 'Fast runner', '2016-12-20', 'Benjamin');
INSERT INTO public.dog ("name", description, dob, owner) VALUES ('Sadie', 'Loves attention', '2021-01-01', 'Amelia');
INSERT INTO public.dog ("name", description, dob, owner) VALUES ('Zeus', 'Strong and majestic', '2019-04-11', 'Lucas');
INSERT INTO public.dog ("name", description, dob, owner) VALUES ('Roxy', 'Fun-loving', '2020-06-22', 'Ella');
INSERT INTO public.dog ("name", description, dob, owner) VALUES ('Cooper', 'Good watchdog', '2018-02-14', 'Henry');
INSERT INTO public.dog ("name", description, dob, owner) VALUES ('Luna', 'Quiet and loving', '2017-07-17', 'Ava');
INSERT INTO public.dog ("name", description, dob, owner) VALUES ('Duke', 'Adventurous', '2015-10-09', 'Alexander');
INSERT INTO public.dog ("name", description, dob, owner) VALUES ('Zoe', 'Always happy', '2022-01-19', 'Grace');
INSERT INTO public.dog ("name", description, dob, owner) VALUES ('Harley', 'Enjoys car rides', '2016-06-06', 'Daniel');
INSERT INTO public.dog ("name", description, dob, owner) VALUES ('Ellie', 'Very playful', '2018-03-30', 'Victoria');
INSERT INTO public.dog ("name", description, dob, owner) VALUES ('Oscar', 'Lap dog', '2019-05-16', 'Matthew');
INSERT INTO public.dog ("name", description, dob, owner) VALUES ('Riley', 'Easily trained', '2020-11-04', 'Hannah');
INSERT INTO public.dog ("name", description, dob, owner) VALUES ('Gracie', 'Independent', '2017-08-27', 'Andrew');
INSERT INTO public.dog ("name", description, dob, owner) VALUES ('Buster', 'Big barker', '2015-04-13', 'Emily');
INSERT INTO public.dog ("name", description, dob, owner) VALUES ('Ruby', 'Red-haired beauty', '2016-01-25', 'Nathan');
INSERT INTO public.dog ("name", description, dob, owner) VALUES ('Sam', 'Loves the beach', '2021-09-07', 'Lily');
INSERT INTO public.dog ("name", description, dob, owner) VALUES ('Chloe', 'Affectionate and smart', '2019-02-02', 'Ryan');
INSERT INTO public.dog ("name", description, dob, owner) VALUES ('Boomer', 'Gentle giant', '2020-07-12', 'Zoe');
INSERT INTO public.dog ("name", description, dob, owner) VALUES ('Penny', 'Shy but sweet', '2017-12-05', 'Aaron');
INSERT INTO public.dog ("name", description, dob, owner) VALUES ('Finn', 'Loyal companion', '2018-10-21', 'Madison');
INSERT INTO public.dog ("name", description, dob, owner) VALUES ('Nala', 'Queen of the house', '2016-03-19', 'Jacob');
INSERT INTO public.dog ("name", description, dob, owner) VALUES ('Bentley', 'Loves to fetch', '2015-07-01', 'Samantha');
INSERT INTO public.dog ("name", description, dob, owner) VALUES ('Stella', 'Always smiling', '2022-02-14', 'Logan');
INSERT INTO public.dog ("name", description, dob, owner) VALUES ('Gus', 'Chill and calm', '2021-05-25', 'Sophia');
INSERT INTO public.dog ("name", description, dob, owner) VALUES ('Milo', 'Always hungry', '2019-11-09', 'Oliver');
INSERT INTO public.dog ("name", description, dob, owner) VALUES ('Coco', 'Loves belly rubs', '2020-04-03', 'Isabelle');
INSERT INTO public.dog ("name", description, dob, owner) VALUES ('Henry', 'Proud and loyal', '2017-06-11', 'Eleanor');
INSERT INTO public.dog ("name", description, dob, owner) VALUES ('Abby', 'Sassy and sweet', '2018-09-28', 'Sebastian');
commit;

#CREATE EXTENSION vector;
#CREATE EXTENSION hstore;
#CREATE EXTENSION  "uuid-ossp";;
#GRANT CREATE ON DATABASE bedrockdb TO bedrockuser;
#GRANT USAGE ON SCHEMA public TO bedrockuser;
#GRANT USAGE, CREATE ON SCHEMA public TO bedrockuser;
#ALTER USER bedrockuser WITH SUPERUSER;
