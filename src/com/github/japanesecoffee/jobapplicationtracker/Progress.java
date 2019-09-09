package com.github.japanesecoffee.jobapplicationtracker;
import java.util.Date;

public class Progress {
	private int progress_id;
	private boolean application_unfinished;
	private Date applied;
	private Date interview1;
	private Date interview2;
	private Date interview3;
	private Date offer_extended;
	private Date acceptedOrRejected;
	private int job_entry_id;
	
	public Progress(Date applied, Date interview1, Date interview2,
			Date interview3, Date offer_extended, Date acceptedOrRejected) {
		super();
		this.progress_id = progress_id;
		this.application_unfinished = application_unfinished;
		this.applied = applied;
		this.interview1 = interview1;
		this.interview2 = interview2;
		this.interview3 = interview3;
		this.offer_extended = offer_extended;
		this.acceptedOrRejected = acceptedOrRejected;
		this.job_entry_id = job_entry_id;
	}

	public int getProgress_id() {
		return progress_id;
	}

	public boolean isApplication_unfinished() {
		return application_unfinished;
	}

	public Date getApplied() {
		return applied;
	}

	public Date getInterview1() {
		return interview1;
	}

	public Date getInterview2() {
		return interview2;
	}

	public Date getInterview3() {
		return interview3;
	}

	public Date getOffer_extended() {
		return offer_extended;
	}

	public Date getAcceptedOrRejected() {
		return acceptedOrRejected;
	}

	public int getJob_entry_id() {
		return job_entry_id;
	}
	
	

}
