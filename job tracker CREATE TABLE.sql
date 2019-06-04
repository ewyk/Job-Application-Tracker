CREATE TABLE job_entry (
	job_entry_id	INT			NOT NULL	AUTO_INCREMENT,
    company			VARCHAR(30)	NOT NULL,
    position		VARCHAR(30)	NOT NULL,
    location		VARCHAR(30) NOT NULL,
    industry		VARCHAR(30),
    notes			VARCHAR(30),
	PRIMARY KEY (job_entry_id)
);

CREATE TABLE progress (
	progress_id					INT			NOT NULL	AUTO_INCREMENT,
    application_unfinished		BOOLEAN		NOT NULL,
    applied						DATE,
    rejected					DATE,
    first_interview				DATE,
    final_interview				DATE,
    offer_extended				DATE,
    offer_accepted				DATE,
    job_entry_id				INT			NOT NULL,
    PRIMARY KEY (progress_id),
    FOREIGN KEY (job_entry_id) REFERENCES job_entry (job_entry_id)
);