--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6rc1
-- Dumped by pg_dump version 9.6rc1

-- Started on 2017-01-05 12:58:06

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET search_path = public, pg_catalog;

SET default_with_oids = false;

--
-- TOC entry 208 (class 1259 OID 49435)
-- Name: ething_feature; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE ething_feature (
    id integer NOT NULL,
    name text NOT NULL,
    description text,
    thingid integer NOT NULL,
    effect text NOT NULL
);


--
-- TOC entry 207 (class 1259 OID 49421)
-- Name: ething_thing; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE ething_thing (
    id integer NOT NULL,
    idhash text NOT NULL,
    name text NOT NULL,
    status text NOT NULL,
    userid integer NOT NULL,
    access text NOT NULL,
    url text NOT NULL,
    url2 text,
    description text,
    thingtype integer NOT NULL
);


--
-- TOC entry 213 (class 1259 OID 49491)
-- Name: ething_thingimage; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE ething_thingimage (
    image bytea NOT NULL,
    thingid integer NOT NULL,
    id integer NOT NULL
);


--
-- TOC entry 212 (class 1259 OID 49478)
-- Name: ething_thingtype; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE ething_thingtype (
    name text NOT NULL,
    id integer NOT NULL
);


--
-- TOC entry 206 (class 1259 OID 49394)
-- Name: ething_user; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE ething_user (
    id integer NOT NULL,
    name text NOT NULL,
    login text NOT NULL,
    password text NOT NULL,
    email text NOT NULL,
    role text NOT NULL,
    activation text NOT NULL
);


--
-- TOC entry 209 (class 1259 OID 49462)
-- Name: feature_id; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE feature_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 9000000
    CACHE 1;


--
-- TOC entry 210 (class 1259 OID 49466)
-- Name: thing_id; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE thing_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 9000000
    CACHE 1;


--
-- TOC entry 214 (class 1259 OID 49500)
-- Name: thingimage_id; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE thingimage_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 100000000000
    CACHE 1;


--
-- TOC entry 215 (class 1259 OID 49502)
-- Name: thingtype_id; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE thingtype_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 1000000000
    CACHE 1;


--
-- TOC entry 211 (class 1259 OID 49468)
-- Name: user_id; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE user_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 9000000
    CACHE 1;


--
-- TOC entry 2087 (class 2606 OID 49403)
-- Name: ething_user emial; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY ething_user
    ADD CONSTRAINT emial UNIQUE (email);


--
-- TOC entry 2099 (class 2606 OID 49442)
-- Name: ething_feature ething_feature_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY ething_feature
    ADD CONSTRAINT ething_feature_pkey PRIMARY KEY (id);


--
-- TOC entry 2095 (class 2606 OID 49428)
-- Name: ething_thing ething_thing_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY ething_thing
    ADD CONSTRAINT ething_thing_pkey PRIMARY KEY (id);


--
-- TOC entry 2103 (class 2606 OID 49505)
-- Name: ething_thingimage ething_thingimage_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY ething_thingimage
    ADD CONSTRAINT ething_thingimage_pkey PRIMARY KEY (id);


--
-- TOC entry 2101 (class 2606 OID 49513)
-- Name: ething_thingtype ething_thingtype_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY ething_thingtype
    ADD CONSTRAINT ething_thingtype_pkey PRIMARY KEY (id);


--
-- TOC entry 2089 (class 2606 OID 49405)
-- Name: ething_user login; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY ething_user
    ADD CONSTRAINT login UNIQUE (login);


--
-- TOC entry 2091 (class 2606 OID 49407)
-- Name: ething_user name; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY ething_user
    ADD CONSTRAINT name UNIQUE (name);


--
-- TOC entry 2093 (class 2606 OID 49401)
-- Name: ething_user user_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY ething_user
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- TOC entry 2104 (class 1259 OID 49511)
-- Name: fki_thingid; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_thingid ON ething_thingimage USING btree (thingid);


--
-- TOC entry 2096 (class 1259 OID 49519)
-- Name: fki_thingtype_id; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_thingtype_id ON ething_thing USING btree (thingtype);


--
-- TOC entry 2097 (class 1259 OID 49434)
-- Name: fki_userid; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_userid ON ething_thing USING btree (userid);


--
-- TOC entry 2107 (class 2606 OID 49443)
-- Name: ething_feature thingid; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY ething_feature
    ADD CONSTRAINT thingid FOREIGN KEY (thingid) REFERENCES ething_thing(id);


--
-- TOC entry 2108 (class 2606 OID 49506)
-- Name: ething_thingimage thingid; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY ething_thingimage
    ADD CONSTRAINT thingid FOREIGN KEY (thingid) REFERENCES ething_thing(id);


--
-- TOC entry 2105 (class 2606 OID 49514)
-- Name: ething_thing thingtype_id; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY ething_thing
    ADD CONSTRAINT thingtype_id FOREIGN KEY (thingtype) REFERENCES ething_thingtype(id);


--
-- TOC entry 2106 (class 2606 OID 49429)
-- Name: ething_thing user_id; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY ething_thing
    ADD CONSTRAINT user_id FOREIGN KEY (userid) REFERENCES ething_user(id);


-- Completed on 2017-01-05 12:58:07

--
-- PostgreSQL database dump complete
--

