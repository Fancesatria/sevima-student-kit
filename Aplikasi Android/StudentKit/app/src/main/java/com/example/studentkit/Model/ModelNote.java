package com.example.studentkit.Model;

public class ModelNote {
    public String title, description, created_at;
    public int id, idpengguna;

    public ModelNote(String title, String description, int idpengguna) {
        this.title = title;
        this.description = description;
        this.idpengguna = idpengguna;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdpengguna() {
        return idpengguna;
    }

    public void setIdpengguna(int idpengguna) {
        this.idpengguna = idpengguna;
    }
}
