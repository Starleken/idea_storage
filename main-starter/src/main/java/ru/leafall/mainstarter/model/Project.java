package ru.leafall.mainstarter.model;

public class Project {
    private Long id;

    private String name;

    private String idea;

    private String shortDescription;

    private String fullDescription;

    private String reasonForPurchase;

    private Double price;

    private Long createdAt;

    private Long expiredAt;

    private Long finishedAt;

    private Boolean isVisible;

    public Project() {
    }

    public Project(Long id, String name, String idea, String shortDescription, String fullDescription, String reasonForPurchase, Double price, Long createdAt, Long expiredAt, Long finishedAt, Boolean isVisible) {
        this.id = id;
        this.name = name;
        this.idea = idea;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.reasonForPurchase = reasonForPurchase;
        this.price = price;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
        this.finishedAt = finishedAt;
        this.isVisible = isVisible;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdea() {
        return idea;
    }

    public void setIdea(String idea) {
        this.idea = idea;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public String getReasonForPurchase() {
        return reasonForPurchase;
    }

    public void setReasonForPurchase(String reasonForPurchase) {
        this.reasonForPurchase = reasonForPurchase;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(Long expiredAt) {
        this.expiredAt = expiredAt;
    }

    public Long getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(Long finishedAt) {
        this.finishedAt = finishedAt;
    }

    public Boolean getVisible() {
        return isVisible;
    }

    public void setVisible(Boolean visible) {
        isVisible = visible;
    }
}
