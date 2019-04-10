package io.sbelkin.azure.computervision.models;

public class Beer {

    private String beer;
    private String company;

    public Beer() {
    }

    public String getBeer() {
        return beer;
    }

    public void setBeer(String beer) {
        this.beer = beer;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Beer{" +
                "beer='" + beer + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}
