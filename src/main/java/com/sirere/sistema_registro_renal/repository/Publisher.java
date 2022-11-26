package com.sirere.sistema_registro_renal.repository;

public interface Publisher {
    public <T> boolean publish(T report);
}
