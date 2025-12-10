package org.example.authentication;

public class DiagramInfo {
    int working;
    int closed;
    int tech_remont;

    public DiagramInfo(int working, int closed, int tech_remont) {
        this.working = working;
        this.closed = closed;
        this.tech_remont = tech_remont;
    }

    public int getWorking() {
        return working;
    }

    public void setWorking(int working) {
        this.working = working;
    }

    public int getClosed() {
        return closed;
    }

    public void setClosed(int closed) {
        this.closed = closed;
    }

    public int getTech_remont() {
        return tech_remont;
    }

    public void setTech_remont(int tech_remont) {
        this.tech_remont = tech_remont;
    }

    public void addWorking(int i){ working += i;}
    public void addClosed(int i) { closed += i; }
    public  void  addTech_remont(int i){ tech_remont += i; }
}
