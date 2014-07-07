package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class ManagedEntity implements Serializable, Comparable<ManagedEntity>
{
	@Id
	@GeneratedValue()
	protected int id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false, nullable = false)
	private Date dateCreated;
	
	public ManagedEntity ()
	{
		dateCreated = new Date();
	}
	
	public Date getDateCreated()
	{
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated)
	{
		this.dateCreated = dateCreated;
	}

	public int compareTo (ManagedEntity e)
	{
		return this.id - e.id;
	}
	
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}
	
	@Override
	public boolean equals (Object o)
	{
		return this.id == ((ManagedEntity)o).id;
	}	
}