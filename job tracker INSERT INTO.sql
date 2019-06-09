INSERT INTO job_entry (company, position, location, industry)
VALUES ('Bank of America', 'Global Technology Summer Analyst',
	'Singapore', 'Banks & Credit Unions');
INSERT INTO job_entry (company, position, location, industry)
VALUES ('Amazon', 'Software Development Engineer', 'Seattle, WA',
	'Internet');
INSERT INTO job_entry (company, position, location, industry)
VALUES ('NCR', 'SharePoint Developer', 'Atlanta, GA', 
	'Computer Hardware & Software');

INSERT INTO progress (application_unfinished, applied, rejected, 
	first_interview)
VALUES (false, DATE '2018-12-21', DATE '2019-03-13', 
	DATE '2018-12-30');
INSERT INTO progress (application_unfinished, applied, rejected, 
	first_interview)
VALUES (false, DATE '2018-12-22', DATE '2019-02-02',
	DATE '2019-02-01');
INSERT INTO progress (application_unfinished, applied, rejected, 
	first_interview, final_interview)
VALUES (false, DATE '2019-04-03', DATE '2019-04-18',
	DATE '2019-04-03', DATE '2019-04-12');