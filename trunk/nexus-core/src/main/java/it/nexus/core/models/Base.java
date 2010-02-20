package it.nexus.core.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@MappedSuperclass
public class Base {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(length = 32)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Transient
    public boolean isNew()
    {
        return (this.id ==null);
    }
}
