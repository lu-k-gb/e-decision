package interfaces;

import com.example.edecision.model.PropositionEntity;

public interface PropositionServiceInterface {
	public abstract void Submit(PropositionEntity product);
	public abstract void Amend(String id, PropositionEntity product);
	public abstract void Escalade(String id);
	public abstract void Support(String id);
	public abstract void Remove(String id);	
}
