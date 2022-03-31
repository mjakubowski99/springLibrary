package com.library.library.entities;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

public class WithTimestampsEntity {

    private Date created_at;

    private Date updated_at;

    @PrePersist
    protected void onCreate(){
        created_at = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        updated_at = new Date();
    }

    public Date getCreatedAt(){
        return this.created_at;
    }

    public Date getUpdatedAt(){
        return this.updated_at;
    }
}
