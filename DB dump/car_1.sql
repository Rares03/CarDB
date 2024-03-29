--
-- PostgreSQL database dump
--

-- Dumped from database version 16.0
-- Dumped by pg_dump version 16.0

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: aspiration; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.aspiration (
    aspirationid integer NOT NULL,
    type character varying(50)
);


ALTER TABLE public.aspiration OWNER TO postgres;

--
-- Name: aspiration_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.aspiration ALTER COLUMN aspirationid ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.aspiration_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: aspiration_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.aspiration_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.aspiration_seq OWNER TO postgres;

--
-- Name: car; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.car (
    id integer NOT NULL,
    makeid integer NOT NULL,
    modelid integer NOT NULL,
    price integer,
    imageid integer
);


ALTER TABLE public.car OWNER TO postgres;

--
-- Name: car_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.car ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.car_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: car_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.car_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.car_seq OWNER TO postgres;

--
-- Name: configuration; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.configuration (
    configid integer NOT NULL,
    trimlevel character varying(50),
    interiorcolor character varying(50)
);


ALTER TABLE public.configuration OWNER TO postgres;

--
-- Name: configuration_configid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.configuration ALTER COLUMN configid ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.configuration_configid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: configuration_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.configuration_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.configuration_seq OWNER TO postgres;

--
-- Name: engine; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.engine (
    engineid integer NOT NULL,
    enginename character varying(50),
    displacement integer,
    horsepower integer,
    torque integer,
    aspirationid integer,
    fuelid integer
);


ALTER TABLE public.engine OWNER TO postgres;

--
-- Name: engine_engineid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.engine ALTER COLUMN engineid ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.engine_engineid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: engine_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.engine_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.engine_seq OWNER TO postgres;

--
-- Name: fuel; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.fuel (
    fuelid integer NOT NULL,
    type character varying(50),
    id integer NOT NULL
);


ALTER TABLE public.fuel OWNER TO postgres;

--
-- Name: fuel_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.fuel ALTER COLUMN fuelid ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.fuel_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: fuel_id_seq1; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.fuel_id_seq1
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.fuel_id_seq1 OWNER TO postgres;

--
-- Name: fuel_id_seq1; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.fuel_id_seq1 OWNED BY public.fuel.id;


--
-- Name: fuel_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.fuel_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.fuel_seq OWNER TO postgres;

--
-- Name: images; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.images (
    imageid integer NOT NULL,
    image_url character varying(255)
);


ALTER TABLE public.images OWNER TO postgres;

--
-- Name: images_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.images_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.images_id_seq OWNER TO postgres;

--
-- Name: images_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.images_id_seq OWNED BY public.images.imageid;


--
-- Name: images_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.images_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.images_seq OWNER TO postgres;

--
-- Name: make; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.make (
    makeid integer NOT NULL,
    makename character varying(50),
    country character varying(50),
    image character varying(255)
);


ALTER TABLE public.make OWNER TO postgres;

--
-- Name: make_makeid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.make ALTER COLUMN makeid ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.make_makeid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: make_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.make_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.make_seq OWNER TO postgres;

--
-- Name: model; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.model (
    modelname character varying(50),
    modelid integer NOT NULL,
    engineid integer,
    configid integer
);


ALTER TABLE public.model OWNER TO postgres;

--
-- Name: model_modelid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.model_modelid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.model_modelid_seq OWNER TO postgres;

--
-- Name: model_modelid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.model_modelid_seq OWNED BY public.model.modelid;


--
-- Name: model_modelid_seq1; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.model ALTER COLUMN modelid ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.model_modelid_seq1
    START WITH 6
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: model_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.model_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.model_seq OWNER TO postgres;

--
-- Name: test_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.test_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.test_seq OWNER TO postgres;

--
-- Name: fuel id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fuel ALTER COLUMN id SET DEFAULT nextval('public.fuel_id_seq1'::regclass);


--
-- Name: images imageid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.images ALTER COLUMN imageid SET DEFAULT nextval('public.images_id_seq'::regclass);


--
-- Data for Name: aspiration; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.aspiration (aspirationid, type) FROM stdin;
1	Naturally Aspirated
3	Twin Turbocharged
4	Supercharged
5	Electric
6	Quad Turbocharged
2	TurboTurbocharged
\.


--
-- Data for Name: car; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.car (id, makeid, modelid, price, imageid) FROM stdin;
2	45	10	30000	53
6	30	11	20000	55
82	15	52	40000	94
86	29	56	80000	98
74	32	44	100000	86
85	34	55	30000	97
55	17	25	70000	67
62	1	32	160000	74
83	30	53	30000	95
50	2	20	60000	62
71	13	41	300000	83
78	24	48	100000	90
63	3	33	350000	75
64	4	34	65000	76
66	35	36	70000	78
77	33	47	25000	89
56	37	26	60000	68
58	5	28	200000	70
51	44	21	80000	63
48	21	18	50000	60
61	39	31	45000	73
52	10	22	40000	64
89	47	59	50000	101
80	46	50	30000	92
121	64	88	5000	129
5	15	7	5000	57
1	46	6	5000	56
128	4	95	3000	136
54	11	24	32000	66
94	23	64	33000	106
79	28	49	90000	91
70	20	40	50000	82
65	42	35	450000	77
67	6	37	60000	79
76	12	46	65000	88
60	14	30	30000	72
68	38	38	90000	80
49	19	19	25000	61
53	43	23	35000	65
88	36	58	115000	100
91	7	61	3000000	103
81	22	51	65000	93
73	45	43	28000	85
69	16	39	55000	81
75	8	45	45000	87
90	18	60	22000	102
93	25	63	45000	105
72	9	42	75000	84
57	26	27	55000	69
59	41	29	75000	71
92	31	62	300000	104
87	40	57	2000000	99
84	27	54	100000	96
\.


--
-- Data for Name: configuration; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.configuration (configid, trimlevel, interiorcolor) FROM stdin;
1	GT-APEX	BLACK
2	GL	BLACK
3	GT	BLACK
5	Infini	Black
6	10th Anniversary	Black
29	GTC	Black
35	R-Dynamic SE	Ebony
36	Limited	Camel
37	Ti Sport	Red/Black
38	Long Range	Cream
39	SS	Jet Black
40	Touring	Java Brown
41	Limited	Black
42	Denali	Cocoa/Dark Atmosphere
43	Premium Plus	Parchment
44	Reserve	Sandstone
45	V8	Linen
46	Adventure	Forest Edge
47	Abarth	Rosso Red
48	Laramie	Light Frost Beige/Mountain Brown
49	Base	Ebony
50	Volante	Chancellor Red
51	Premium Plus	Okapi Brown
52	Standard Wheelbase	Mugello Red
53	Premier Edition	Forest Green
54	M550i xDrive	Cognac Dakota Leather
55	4S	Marsala
56	Sport	Gray Leather
57	Red Sport 400	Graphite
58	Spider	Nero
59	Luxury	Gideon/Ebony
60	XSE V6	Cockpit Red
61	S 560	Designo Mystic Blue
62	Avenir	Chestnut
63	Scat Pack	Ruby Red/Black
64	John Cooper Works	Carbon Black
65	HSE	Vintage Tan
66	Dream Edition	Obsidian Black
67	R-Line	Titan Black
68	Overland	Ski Gray/Indigo
69	Premium	Dark Ceramic
70	Grand Touring	Parchment
71	GT	Venom Red
72	SEL	Diamond Red
73	S Q4 GranSport	Sabbia
74	Signature Edition	Ultraviolet
75	Track Edition	Amber Red
76	Inscription	Maroon Brown
77	Type R	Black/Red
78	Sport	Gaucho
79	Performance	Vintage Tan
80	F Sport	Circuit Red
81	GT2	Sunflower Yellow
82	Ultimate	Jet Black
84	Audi Concert	Black
\.


--
-- Data for Name: engine; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.engine (engineid, enginename, displacement, horsepower, torque, aspirationid, fuelid) FROM stdin;
91	BLP	1588	115	155	1	1
13	V8N450	6162	455	617	1	1
23	V6H573	3493	573	476	3	1
33	V8N420	6162	420	624	1	1
7	Z16XEP	1587	105	150	1	1
14	H4N180	2387	260	375	2	1
24	V12N715	5204	715	900	3	1
44	I4N187	2488	187	186	2	1
53	W16T1500	8000	1500	1600	6	1
36	V6N310	3649	310	365	1	1
3	302	3025	225	275	1	1
6	13B-Turbo II	1308	182	183	2	1
1	4A-GE	1587	128	110	1	1
5	13B-REW	1308	252	217	3	1
2	ABA	1984	115	122	1	1
20	E150	0	800	1120	5	3
52	I4N180	2000	180	200	1	1
37	V8T485	6417	707	881	4	1
54	V8T710	4000	710	568	2	1
22	V8T350	5654	395	556	1	1
27	E100	0	652	725	5	3
10	V6N300	2488	191	181	1	1
49	E1500	0	1000	1600	5	3
47	I4N166	2360	166	220	1	1
40	E120	0	1080	1133	5	3
21	I4T120	1368	160	183	2	1
34	I4N200	2494	203	184	1	1
55	V6H308	3500	300	400	5	3
38	I3T134	1998	189	207	2	1
45	V6N416	3506	416	410	4	1
30	V6T280	3498	375	391	3	1
15	V6T320	3604	292	352	1	1
16	V8N400	6162	420	624	1	1
31	V6T300	2997	300	400	3	1
28	I6T300	2998	335	332	3	1
43	V8N450	4951	450	410	1	1
19	W12TT600	5950	626	900	3	1
35	V8T450	3982	469	700	3	1
50	V6T600	3800	600	650	3	1
11	I4T350	1995	280	306	2	1
18	V6TT400	2956	400	515	3	1
84	V6T365	3300	365	376	2	1
42	V8N360	6166	475	637	1	1
12	E200	0	670	1050	5	3
26	V12N563	6749	563	900	1	1
29	V6T330	2894	325	450	3	1
51	I4T316	2000	250	350	2	1
41	I4T200	1984	174	206	2	1
39	V6T355	4999	518	625	4	1
9	I4T250	1997	250	365	2	1
32	V8T670	3902	661	760	3	1
25	V6T340	2995	335	500	2	1
48	V6T350	3000	350	500	2	1
17	I4T180	0	408	660	5	3
\.


--
-- Data for Name: fuel; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.fuel (fuelid, type, id) FROM stdin;
2	Diesel	2
3	Electric	3
1	Gasoline	1
\.


--
-- Data for Name: images; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.images (imageid, image_url) FROM stdin;
53	./cars/AE-86.jpg
55	./cars/RX-7_FC3S.jpg
56	./cars/Golf_MKIII.jpg
57	./cars/Mustang.jpg
60	./cars/XF.jpg
61	./cars/Sonata.jpg
85	./cars/Camry.jpg
67	./cars/Yukon.jpg
87	./cars/Enclave.jpg
103	./cars/Chiron.jpg
95	./cars/CX-5.jpg
93	./cars/Grand_Cherokee.jpg
76	./cars/A7.jpg
94	./cars/Mustang_GT.jpg
82	./cars/Q50.jpg
64	./cars/Camaro.jpg
69	./cars/Aviator.jpg
79	./cars/5_Series.jpg
99	./cars/C_Two.jpg
75	./cars/DBS.jpg
73	./cars/1500.jpg
65	./cars/Outback.jpg
100	./cars/GT-R.jpg
62	./cars/Stelvio.jpg
71	./cars/R1T.jpg
83	./cars/488_GTB.jpg
68	./cars/2.jpg
63	./cars/Model_S.jpg
66	./cars/300.jpg
96	./cars/Evora.jpg
90	./cars/Range_Rover.jpg
98	./cars/Ghibli.jpg
101	./cars/XC90.jpg
91	./cars/Air.jpg
70	./cars/Continental_GT.jpg
86	./cars/S-Class.jpg
74	./cars/NSX.jpg
84	./cars/Escalade.jpg
72	./cars/500.jpg
80	./cars/Panamera.jpg
104	./cars/720S.jpg
88	./cars/Charger.jpg
77	./cars/Phantom.jpg
81	./cars/G80.jpg
89	./cars/Cooper.jpg
106	./cars/Stinger.jpg
105	./cars/RX.jpg
97	./cars/Outlander.jpg
102	./cars/Civic.jpg
78	./cars/ES8.jpg
92	./cars/Passat.jpg
129	./cars/Astra.png
136	./cars/A3.png
\.


--
-- Data for Name: make; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.make (makeid, makename, country, image) FROM stdin;
21	Jaguar	United Kingdom	./img/Jaguar.png
19	Hyundai	South Korea	./img/Hyundai.png
2	Alfa Romeo	Italy	./img/Alfa_Romeo.png
44	Tesla	United States	./img/Tesla.png
10	Chevrolet	United States	./img/Chevrolet.png
43	Subaru	Japan	./img/Subaru.png
11	Chrysler	United States	./img/Chrysler.png
17	GMC	United States	./img/GMC.png
37	Polestar	Sweden	./img/Polestar.png
26	Lincoln	United States	./img/Lincoln.png
5	Bentley	United Kingdom	./img/Bentley.png
41	Rivian	United States	./img/Rivian.png
14	Fiat	Italy	./img/Fiat.png
39	Ram	United States	./img/Ram.png
1	Acura	Japan	./img/Acura.png
3	Aston Martin	United Kingdom	./img/Aston_Martin.png
4	Audi	Germany	./img/Audi.png
42	Rolls-Royce	United Kingdom	./img/Rolls_Royce.png
6	BMW	Germany	./img/BMW.png
38	Porsche	Germany	./img/Porsche.png
16	Genesis	South Korea	./img/Genesis.png
20	Infiniti	Japan	./img/Infiniti.png
13	Ferrari	Italy	./img/Ferrari.png
9	Cadillac	United States	./img/Cadillac.png
45	Toyota	Japan	./img/Toyota.png
32	Mercedes-Benz	Germany	./img/Mercedes_Benz.png
8	Buick	United States	./img/Buick.png
12	Dodge	United States	./img/Dodge.png
36	Nissan	China	./img/Nissan.png
33	Mini	United Kingdom	./img/Mini.png
24	Land Rover	United Kingdom	./img/Land_Rover.png
28	Lucid Motors	United States	./img/Lucid_Motors.png
46	Volkswagen	Germany	./img/Volkswagen.png
22	Jeep	United States	./img/Jeep.png
15	Ford	United States	./img/Ford.png
30	Mazda	Japan	./img/Mazda.png
27	Lotus	United Kingdom	./img/Lotus.png
34	Mitsubishi	Japan	./img/Mitsubishi.png
29	Maserati	Italy	./img/Maserati.png
40	Rimac	Croatia	./img/Rimac.png
47	Volvo	Sweden	./img/Volvo.png
18	Honda	Japan	./img/Honda.png
7	Bugatti	France	./img/Bugatti.png
31	McLaren	United Kingdom	./img/McLaren.png
25	Lexus	Japan	./img/Lexus.png
23	Kia	South Korea	./img/Kia.png
35	NIO	Japan	./img/NIO.png
64	Opel	Germany	./img/Opel.png
70	Dacia	Romania	./img/Dacia.png
\.


--
-- Data for Name: model; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.model (modelname, modelid, engineid, configid) FROM stdin;
XC90	59	51	76
Cooper	47	38	64
DBS	33	24	50
Continental GT	28	19	45
G80	39	30	56
Range Rover	48	39	65
Outlander	55	47	72
300	24	15	41
Camry	43	34	60
XF	18	9	35
Mustang GT	52	43	69
GT-R	58	50	75
A7	34	25	51
Camaro	22	13	39
Stinger	64	84	81
RX	63	55	80
Passat	50	41	67
CX-5	53	44	70
Mustang	7	3	3
RX-7 FD3S	8	5	5
RX-7 FC3S	11	6	6
Golf MKIII	6	2	2
AE-86	10	1	1
Air	49	40	66
Charger	46	37	63
Sonata	19	10	36
ES8	36	27	53
Escalade	42	33	59
720S	62	54	79
Chiron	61	53	78
C_Two	57	49	74
Phantom	35	26	52
Outback	23	14	40
Stelvio	20	11	37
Grand Cherokee	51	42	68
Aviator	27	18	44
2	26	17	43
488 GTB	41	32	58
Ghibli	56	48	73
5 Series	37	28	54
1500	31	22	48
Enclave	45	36	62
Civic	60	52	77
Model S	21	12	38
500	30	21	47
NSX	32	23	49
Panamera	38	29	55
Q50	40	31	57
Yukon	25	16	42
Evora	54	45	71
S-Class	44	35	61
R1T	29	20	46
A3	95	91	84
Meriva	88	7	29
\.


--
-- Name: aspiration_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.aspiration_id_seq', 6, true);


--
-- Name: aspiration_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.aspiration_seq', 1, false);


--
-- Name: car_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.car_id_seq', 128, true);


--
-- Name: car_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.car_seq', 1, true);


--
-- Name: configuration_configid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.configuration_configid_seq', 84, true);


--
-- Name: configuration_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.configuration_seq', 1, false);


--
-- Name: engine_engineid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.engine_engineid_seq', 91, true);


--
-- Name: engine_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.engine_seq', 1, false);


--
-- Name: fuel_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.fuel_id_seq', 3, true);


--
-- Name: fuel_id_seq1; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.fuel_id_seq1', 3, true);


--
-- Name: fuel_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.fuel_seq', 1, false);


--
-- Name: images_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.images_id_seq', 136, true);


--
-- Name: images_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.images_seq', 1, false);


--
-- Name: make_makeid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.make_makeid_seq', 70, true);


--
-- Name: make_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.make_seq', 1, true);


--
-- Name: model_modelid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.model_modelid_seq', 5, true);


--
-- Name: model_modelid_seq1; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.model_modelid_seq1', 95, true);


--
-- Name: model_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.model_seq', 1, false);


--
-- Name: test_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.test_seq', 1, false);


--
-- Name: aspiration aspiration_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.aspiration
    ADD CONSTRAINT aspiration_pkey PRIMARY KEY (aspirationid);


--
-- Name: car car_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car
    ADD CONSTRAINT car_pkey PRIMARY KEY (id);


--
-- Name: configuration configuration_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.configuration
    ADD CONSTRAINT configuration_pkey PRIMARY KEY (configid);


--
-- Name: engine engine_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.engine
    ADD CONSTRAINT engine_pkey PRIMARY KEY (engineid);


--
-- Name: fuel fuel_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fuel
    ADD CONSTRAINT fuel_pkey PRIMARY KEY (fuelid);


--
-- Name: images images_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.images
    ADD CONSTRAINT images_pkey PRIMARY KEY (imageid);


--
-- Name: make make_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.make
    ADD CONSTRAINT make_pk UNIQUE (makename);


--
-- Name: make make_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.make
    ADD CONSTRAINT make_pkey PRIMARY KEY (makeid);


--
-- Name: model model_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.model
    ADD CONSTRAINT model_pk UNIQUE (modelname);


--
-- Name: model model_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.model
    ADD CONSTRAINT model_pkey PRIMARY KEY (modelid);


--
-- Name: car car_image_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car
    ADD CONSTRAINT car_image_fk FOREIGN KEY (imageid) REFERENCES public.images(imageid);


--
-- Name: car car_makeid_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car
    ADD CONSTRAINT car_makeid_fk FOREIGN KEY (makeid) REFERENCES public.make(makeid);


--
-- Name: car car_modelid_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car
    ADD CONSTRAINT car_modelid_fk FOREIGN KEY (modelid) REFERENCES public.model(modelid);


--
-- Name: engine engine_aspiration_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.engine
    ADD CONSTRAINT engine_aspiration_fk FOREIGN KEY (aspirationid) REFERENCES public.aspiration(aspirationid);


--
-- Name: engine engine_fuel_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.engine
    ADD CONSTRAINT engine_fuel_fk FOREIGN KEY (fuelid) REFERENCES public.fuel(fuelid);


--
-- Name: model model_config_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.model
    ADD CONSTRAINT model_config_fk FOREIGN KEY (configid) REFERENCES public.configuration(configid);


--
-- Name: model model_engineid_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.model
    ADD CONSTRAINT model_engineid_fk FOREIGN KEY (engineid) REFERENCES public.engine(engineid);


--
-- PostgreSQL database dump complete
--

