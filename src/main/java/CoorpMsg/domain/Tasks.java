package CoorpMsg.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Tasks {
    @Id
    @GeneratedValue (strategy= GenerationType.AUTO)
    private Integer id;

    private Integer idProj;

    private String description;

    private String deadline;

    private String stage;

    public Tasks(){

    }

    public Tasks(Integer idProj, String description, String deadline){
        this.idProj = idProj;
        this.deadline = deadline;
        this.description = description;
        this.stage = "На рассмотрении";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public Integer getIdProj() {
        return idProj;
    }

    public void setIdProj(Integer idProj) {
        this.idProj = idProj;
    }
}
