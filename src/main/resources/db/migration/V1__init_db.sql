create table ceo (
    id bigint not null,
    name varchar(255),
    company_id bigint,
    primary key (id)
);

create table company (
    id bigint not null,
    name varchar(255),
    ceo_id bigint,
    country_id bigint,
    primary key (id)
);

create table country (
    id bigint not null,
    name varchar(255),
    primary key (id)
);

create table sphere (
    id bigint not null,
    name varchar(255),
    primary key (id)
);

create table company_spheres (
    company_id bigint not null,
    spheres_id bigint not null
);

create table country_companies (
    country_id bigint not null,
    companies_id bigint not null
);

create table sphere_companies (
    sphere_id bigint not null,
    companies_id bigint not null
);

alter table ceo add constraint FK_ceo_company_id foreign key (company_id) references company;

alter table company add constraint FK_company_ceo_id foreign key (ceo_id) references ceo;

alter table company add constraint FK_company_country_id foreign key (country_id) references country;

alter table company_spheres add constraint FK_company_spheres_spheres_id foreign key (spheres_id) references sphere;

alter table company_spheres add constraint FK_company_spheres_company_id foreign key (company_id) references company;

alter table country_companies add constraint FK_country_companies_companies_id foreign key (companies_id) references company;

alter table country_companies add constraint FK_country_companies_country_id foreign key (country_id) references country;

alter table sphere_companies add constraint FK_sphere_companies_companies_id foreign key (companies_id) references company;

alter table sphere_companies add constraint FK_sphere_companies_sphere_id foreign key (sphere_id) references sphere;