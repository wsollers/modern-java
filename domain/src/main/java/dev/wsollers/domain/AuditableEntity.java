package dev.wsollers.domain;

import java.io.Serializable;

import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;
import javax.persistence.Column;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public abstract class AuditableEntity implements Serializable {
  
  private static final long serialVersionUID = 1L;

  @JsonIgnore
  @Column(name = "CREATED_BY", nullable = false, updatable = false)
  private String createdBy;

  @JsonIgnore
  @Column(name = "CREATED_DATE", nullable = false, updatable = false)
  private LocalDateTime createdDate;

  @JsonIgnore
  @Column(name = "LAST_MODIFIED_BY")
  private String lastModifiedBy;

  @JsonIgnore
  @Column(name = "LAST_MODIFIED_DATE")
  private LocalDateTime lastModifiedDate;

  @PrePersist
  public void prePersist() {
    createdDate = LocalDateTime.now();
  }

  @PreUpdate
  public void preUpdate() {
    lastModifiedDate = LocalDateTime.now();
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }

  public String getLastModifiedBy() {
    return lastModifiedBy;
  }

  public void setLastModifiedBy(String lastModifiedBy) {
    this.lastModifiedBy = lastModifiedBy;
  }

  public LocalDateTime getLastModifiedDate() {
    return lastModifiedDate;
  }

  public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
  }
}
