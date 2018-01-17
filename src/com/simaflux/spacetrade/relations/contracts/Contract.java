package com.simaflux.spacetrade.relations.contracts;

import com.simaflux.spacetrade.game.date.Date;
import com.simaflux.spacetrade.game.date.DateManager;
import com.simaflux.spacetrade.game.date.Deadline;
import com.simaflux.spacetrade.game.date.DeadlineTimer;
import com.simaflux.spacetrade.objects.resources.StaticResource;
import com.simaflux.spacetrade.relations.PlayerEmpireRelation;

public class Contract implements Deadline {
	
	private final Date endDate;
	
	private final PlayerEmpireRelation relation;

	private final float tickSum, completeSum;
	private final StaticResource[] resources;
	
	public Contract(Date endDate, PlayerEmpireRelation relation, float tickSum, float completeSum, StaticResource...resources) {
		this.endDate = endDate;
		this.relation = relation;
		this.tickSum = tickSum;
		this.completeSum = completeSum;
		this.resources = resources;
		
		if(endDate != null) {
			DateManager.addDeadline(new DeadlineTimer(this, endDate, "endDate"));
		}
	}
	
	public void tick() {
		boolean resourcesDelivered = true;
		
		for(StaticResource r : resources) {
			if(relation.getPlayer().hasResource(r.getName(), r.getAmount())) {
				relation.getPlayer().addQuantity(r.getName(), -r.getAmount());
			} else resourcesDelivered = false;
		}
		
		if(resourcesDelivered) {
			relation.getPlayer().addCash(tickSum);
		} else {
			//TODO empire be mad
		}
	}

	@Override
	public void deadlineReached(String deadlineTag) {
		relation.getPlayer().addCash(completeSum);
		
		relation.terminateContract(this);
	}
	
	public Date getEndDate() {
		return endDate;
	}

}
