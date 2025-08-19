--
-- PostgreSQL database dump
--

-- Dumped from database version 17.5
-- Dumped by pg_dump version 17.5

-- Started on 2025-07-28 09:22:14

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO pg_database_owner;

--
-- TOC entry 4945 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 217 (class 1259 OID 24679)
-- Name: categoria; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.categoria (
    id integer NOT NULL,
    descricao character varying NOT NULL
);


ALTER TABLE public.categoria OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 24684)
-- Name: categoria_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.categoria_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.categoria_id_seq OWNER TO postgres;

--
-- TOC entry 4946 (class 0 OID 0)
-- Dependencies: 218
-- Name: categoria_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.categoria_id_seq OWNED BY public.categoria.id;


--
-- TOC entry 224 (class 1259 OID 24749)
-- Name: compra; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.compra (
    id integer NOT NULL,
    usuario_id integer NOT NULL,
    data_compra timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    status character varying DEFAULT 'PENDENTE'::character varying
);


ALTER TABLE public.compra OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 24748)
-- Name: compra_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.compra_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.compra_id_seq OWNER TO postgres;

--
-- TOC entry 4947 (class 0 OID 0)
-- Dependencies: 223
-- Name: compra_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.compra_id_seq OWNED BY public.compra.id;


--
-- TOC entry 226 (class 1259 OID 24765)
-- Name: item_compra; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.item_compra (
    id integer NOT NULL,
    compra_id integer NOT NULL,
    produto_id integer NOT NULL,
    quantidade integer NOT NULL,
    preco_unitario real NOT NULL
);


ALTER TABLE public.item_compra OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 24764)
-- Name: item_compra_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.item_compra_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.item_compra_id_seq OWNER TO postgres;

--
-- TOC entry 4948 (class 0 OID 0)
-- Dependencies: 225
-- Name: item_compra_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.item_compra_id_seq OWNED BY public.item_compra.id;


--
-- TOC entry 219 (class 1259 OID 24685)
-- Name: produto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.produto (
    id integer NOT NULL,
    preco numeric NOT NULL,
    foto character varying,
    quantidade integer NOT NULL,
    categoria_id integer NOT NULL,
    titulo character varying,
    autor character varying,
    ano integer
);


ALTER TABLE public.produto OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 24690)
-- Name: produto_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.produto_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.produto_id_seq OWNER TO postgres;

--
-- TOC entry 4949 (class 0 OID 0)
-- Dependencies: 220
-- Name: produto_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.produto_id_seq OWNED BY public.produto.id;


--
-- TOC entry 221 (class 1259 OID 24691)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario (
    id integer NOT NULL,
    nome character varying NOT NULL,
    endereco character varying NOT NULL,
    email character varying NOT NULL,
    login character varying NOT NULL,
    senha character varying NOT NULL,
    administrador boolean NOT NULL,
    cpf character varying NOT NULL
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 24696)
-- Name: usuario_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usuario_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.usuario_id_seq OWNER TO postgres;

--
-- TOC entry 4950 (class 0 OID 0)
-- Dependencies: 222
-- Name: usuario_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.usuario_id_seq OWNED BY public.usuario.id;


--
-- TOC entry 4762 (class 2604 OID 24697)
-- Name: categoria id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categoria ALTER COLUMN id SET DEFAULT nextval('public.categoria_id_seq'::regclass);


--
-- TOC entry 4765 (class 2604 OID 24752)
-- Name: compra id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.compra ALTER COLUMN id SET DEFAULT nextval('public.compra_id_seq'::regclass);


--
-- TOC entry 4768 (class 2604 OID 24768)
-- Name: item_compra id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_compra ALTER COLUMN id SET DEFAULT nextval('public.item_compra_id_seq'::regclass);


--
-- TOC entry 4763 (class 2604 OID 24698)
-- Name: produto id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produto ALTER COLUMN id SET DEFAULT nextval('public.produto_id_seq'::regclass);


--
-- TOC entry 4764 (class 2604 OID 24699)
-- Name: usuario id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario ALTER COLUMN id SET DEFAULT nextval('public.usuario_id_seq'::regclass);


--
-- TOC entry 4930 (class 0 OID 24679)
-- Dependencies: 217
-- Data for Name: categoria; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.categoria (id, descricao) VALUES (1, 'Aventura');
INSERT INTO public.categoria (id, descricao) VALUES (3, 'Ficção');
INSERT INTO public.categoria (id, descricao) VALUES (2, 'Romance');


--
-- TOC entry 4937 (class 0 OID 24749)
-- Dependencies: 224
-- Data for Name: compra; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.compra (id, usuario_id, data_compra, status) VALUES (1, 20, '2025-07-27 00:00:00', 'Processando');
INSERT INTO public.compra (id, usuario_id, data_compra, status) VALUES (2, 20, '2025-07-27 00:00:00', 'Processando');
INSERT INTO public.compra (id, usuario_id, data_compra, status) VALUES (3, 20, '2025-07-27 00:00:00', 'Processando');
INSERT INTO public.compra (id, usuario_id, data_compra, status) VALUES (4, 20, '2025-07-27 00:00:00', 'Processando');
INSERT INTO public.compra (id, usuario_id, data_compra, status) VALUES (5, 20, '2025-07-28 00:00:00', 'Processando');
INSERT INTO public.compra (id, usuario_id, data_compra, status) VALUES (6, 20, '2025-07-28 00:00:00', 'Processando');
INSERT INTO public.compra (id, usuario_id, data_compra, status) VALUES (7, 20, '2025-07-28 08:09:04.289', 'Processando');


--
-- TOC entry 4939 (class 0 OID 24765)
-- Dependencies: 226
-- Data for Name: item_compra; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.item_compra (id, compra_id, produto_id, quantidade, preco_unitario) VALUES (1, 1, 13, 1, 123);
INSERT INTO public.item_compra (id, compra_id, produto_id, quantidade, preco_unitario) VALUES (2, 2, 16, 1, 3);
INSERT INTO public.item_compra (id, compra_id, produto_id, quantidade, preco_unitario) VALUES (3, 2, 20, 1, 12);
INSERT INTO public.item_compra (id, compra_id, produto_id, quantidade, preco_unitario) VALUES (4, 3, 13, 3, 123);
INSERT INTO public.item_compra (id, compra_id, produto_id, quantidade, preco_unitario) VALUES (5, 3, 16, 1, 3);
INSERT INTO public.item_compra (id, compra_id, produto_id, quantidade, preco_unitario) VALUES (6, 4, 13, 1, 123);
INSERT INTO public.item_compra (id, compra_id, produto_id, quantidade, preco_unitario) VALUES (7, 4, 20, 1, 12);
INSERT INTO public.item_compra (id, compra_id, produto_id, quantidade, preco_unitario) VALUES (8, 5, 13, 1, 123);
INSERT INTO public.item_compra (id, compra_id, produto_id, quantidade, preco_unitario) VALUES (9, 6, 13, 3, 123);
INSERT INTO public.item_compra (id, compra_id, produto_id, quantidade, preco_unitario) VALUES (10, 7, 13, 1, 123);


--
-- TOC entry 4932 (class 0 OID 24685)
-- Dependencies: 219
-- Data for Name: produto; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.produto (id, preco, foto, quantidade, categoria_id, titulo, autor, ano) VALUES (14, 0, NULL, 0, 2, 'Livro sem título', 'Anônimo', 1);
INSERT INTO public.produto (id, preco, foto, quantidade, categoria_id, titulo, autor, ano) VALUES (13, 129, '/home/leoomoreira/Upload/13.jpg', 7, 1, 'As Crônicas de Nárnia', 'de Lewis, C. S', 2009);
INSERT INTO public.produto (id, preco, foto, quantidade, categoria_id, titulo, autor, ano) VALUES (16, 22.48, '/home/leoomoreira/Upload/16.jpg', 3, 3, 'Infocracia: Digitalização e a crise da democracia', ' Byung-Chul Han ', 2022);
INSERT INTO public.produto (id, preco, foto, quantidade, categoria_id, titulo, autor, ano) VALUES (20, 69.24, '/home/leoomoreira/Upload/20.jpg', 12, 3, '1984', ' GEORGE ORWELL', 1950);


--
-- TOC entry 4934 (class 0 OID 24691)
-- Dependencies: 221
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.usuario (id, nome, endereco, email, login, senha, administrador, cpf) VALUES (14, 'admi', 'a', 'a@gmail.cm', '1', '1', true, '123234');
INSERT INTO public.usuario (id, nome, endereco, email, login, senha, administrador, cpf) VALUES (2, 'José', 's', 's@gmail.com', 's', 's', false, 's');
INSERT INTO public.usuario (id, nome, endereco, email, login, senha, administrador, cpf) VALUES (13, 'Maria', 'q', 'q@gmail.com', 'q', 'q', false, 'q');
INSERT INTO public.usuario (id, nome, endereco, email, login, senha, administrador, cpf) VALUES (20, 'Rico', 'abc', 'gmail@gmail.com', 'w', 'w', false, '123');


--
-- TOC entry 4951 (class 0 OID 0)
-- Dependencies: 218
-- Name: categoria_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.categoria_id_seq', 4, true);


--
-- TOC entry 4952 (class 0 OID 0)
-- Dependencies: 223
-- Name: compra_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.compra_id_seq', 7, true);


--
-- TOC entry 4953 (class 0 OID 0)
-- Dependencies: 225
-- Name: item_compra_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.item_compra_id_seq', 10, true);


--
-- TOC entry 4954 (class 0 OID 0)
-- Dependencies: 220
-- Name: produto_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.produto_id_seq', 22, true);


--
-- TOC entry 4955 (class 0 OID 0)
-- Dependencies: 222
-- Name: usuario_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuario_id_seq', 22, true);


--
-- TOC entry 4770 (class 2606 OID 24701)
-- Name: categoria categoria_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categoria
    ADD CONSTRAINT categoria_pkey PRIMARY KEY (id);


--
-- TOC entry 4778 (class 2606 OID 24758)
-- Name: compra compra_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.compra
    ADD CONSTRAINT compra_pkey PRIMARY KEY (id);


--
-- TOC entry 4780 (class 2606 OID 24770)
-- Name: item_compra item_compra_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_compra
    ADD CONSTRAINT item_compra_pkey PRIMARY KEY (id);


--
-- TOC entry 4772 (class 2606 OID 24703)
-- Name: produto produto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produto
    ADD CONSTRAINT produto_pkey PRIMARY KEY (id);


--
-- TOC entry 4774 (class 2606 OID 24705)
-- Name: usuario usuario_login_ukey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_login_ukey UNIQUE (login);


--
-- TOC entry 4776 (class 2606 OID 24707)
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);


--
-- TOC entry 4783 (class 2606 OID 24771)
-- Name: item_compra id_compra_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_compra
    ADD CONSTRAINT id_compra_fk FOREIGN KEY (compra_id) REFERENCES public.compra(id);


--
-- TOC entry 4784 (class 2606 OID 24776)
-- Name: item_compra id_produto_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_compra
    ADD CONSTRAINT id_produto_fk FOREIGN KEY (produto_id) REFERENCES public.produto(id);


--
-- TOC entry 4782 (class 2606 OID 24759)
-- Name: compra id_usuario_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.compra
    ADD CONSTRAINT id_usuario_fk FOREIGN KEY (usuario_id) REFERENCES public.usuario(id);


--
-- TOC entry 4781 (class 2606 OID 24708)
-- Name: produto produto_categoria_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produto
    ADD CONSTRAINT produto_categoria_id_fkey FOREIGN KEY (categoria_id) REFERENCES public.categoria(id) ON UPDATE CASCADE ON DELETE CASCADE;


-- Completed on 2025-07-28 09:22:14

--
-- PostgreSQL database dump complete
--

