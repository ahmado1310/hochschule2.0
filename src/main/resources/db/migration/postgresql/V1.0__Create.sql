CREATE TABLE IF NOT EXISTS fakultaet (
                           id UUID PRIMARY KEY,
                           name VARCHAR(255) NOT NULL,
                           dekan_id UUID NOT NULL,
                           FOREIGN KEY (dekan_id) REFERENCES dekan(id)
);

CREATE TABLE IF NOT EXISTS dekan (
                       id UUID PRIMARY KEY,
                       name VARCHAR(32) NOT NULL CHECK (length(name) >= 2)
);

CREATE TABLE IF NOT EXISTS professor (
                           id UUID PRIMARY KEY,
                           name VARCHAR(32) NOT NULL CHECK (length(name) >= 2),
                           buero_id UUID NOT NULL,
                           fakultaet_id UUID NOT NULL,
                           FOREIGN KEY (buero_id) REFERENCES buero(id),
                           FOREIGN KEY (fakultaet_id) REFERENCES fakultaet(id)
);

CREATE TABLE IF NOT EXISTS professor (
                           id UUID PRIMARY KEY,
                           name VARCHAR(32) NOT NULL CHECK (length(name) >= 2),
                           buero_id UUID NOT NULL,
                           fakultaet_id UUID NOT NULL,
                           FOREIGN KEY (buero_id) REFERENCES buero(id),
                           FOREIGN KEY (fakultaet_id) REFERENCES fakultaet(id)
);

CREATE TABLE IF NOT EXISTS buero (
                       id UUID PRIMARY KEY,
                       gebaeude VARCHAR(255) NOT NULL
);
