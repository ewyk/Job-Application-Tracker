package com.github.japanesecoffee.jobapplicationtracker;
import java.util.Date;

public class Progress {
	private int progress_id;
	private boolean application_unfinished;
	private Date applied;
	private Date rejected;
	private Date first_interview;
	private Date final_interview;
	private Date offer_extended;
	private Date offer_accepted;
	private int job_entry_id;
	
	public Progress(int progress_id, boolean application_unfinished, Date applied, Date rejected, Date first_interview,
			Date final_interview, Date offer_extended, Date offer_accepted, int job_entry_id) {
		super();
		this.progress_id = progress_id;
		this.application_unfinished = application_unfinished;
		this.applied = applied;
		this.rejected = rejected;
		this.first_interview = first_interview;
		this.final_interview = final_interview;
		this.offer_extended = offer_extended;
		this.offer_accepted = offer_accepted;
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

	public Date getRejected() {
		return rejected;
	}

	public Date getFirst_interview() {
		return first_interview;
	}

	public Date getFinal_interview() {
		return final_interview;
	}

	public Date getOffer_extended() {
		return offer_extended;
	}

	public Date getOffer_accepted() {
		return offer_accepted;
	}

	public int getJob_entry_id() {
		return job_entry_id;
	}
	
	

}
