package pl.pjatk.nbp.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "currency_query1")
@Schema(description = "Waluty Tabela")
public class CurrencyQuery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID")
    @Column(name = "id")
    private Long id;

    @Schema(description = "Waluta")
    @Column(name = "currency")
    private CurrencyType currency;

    @Schema(description = "Ilość_dni")
    @Column(name = "startDate")
    private LocalDate startDate;

    @Schema(description = "Aktualny Kurs")
    @Column(name = "endDate")
    private LocalDate endDate;

    @Schema(description = "Średnia")
    @Column(name = "calculated_rate")
    private double calculatedRate;

    @Schema(description = "Data")
    @Column(name = "query_date")
    private LocalDate queryDate;

    @Schema(description = "Godzina")
    @Column(name = "query_time")
    private LocalTime queryTime;

    @Schema(description = "Godzina")
    @Column(name = "result_count")
    private int resultCount;

    // konstruktory, gettery, settery

    public CurrencyQuery() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CurrencyType getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyType currency) {
        this.currency = currency;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getCalculatedRate() {
        return calculatedRate;
    }

    public void setCalculatedRate(double calculatedRate) {
        this.calculatedRate = calculatedRate;
    }

    public LocalDate getQueryDate() {
        return queryDate;
    }

    public void setQueryDate(LocalDate queryDate) {
        this.queryDate = queryDate;
    }

    public LocalTime getQueryTime() {
        return queryTime;
    }

    public void setQueryTime(LocalTime queryTime) {
        this.queryTime = queryTime;
    }

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }
}
