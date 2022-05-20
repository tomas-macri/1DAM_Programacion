package com.ctrempleados.dao.impl;

import com.ctrempleados.configuracion.Configuracion;
import com.ctrempleados.dao.Database;
import com.ctrempleados.dao.common.ConstantesDao;
import com.ctrempleados.domain.modelo.Empleado;
import com.ctrempleados.domain.modelo.Franquicia;
import com.ctrempleados.domain.modelo.Nomina;
import com.ctrempleados.domain.modelo.Registro;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;


@Log4j2
public class DatabaseImpl implements Database {

    private final Gson gson;
    private final Configuracion configuracion;

    @Inject
    public DatabaseImpl(Gson gson, Configuracion configuracion) {
        this.gson = gson;
        this.configuracion = configuracion;
    }

    @Override
    public Either<String, List<Empleado>> readJSONEmpleados() {

        Type userListType = new TypeToken<List<Empleado>>() {
        }.getType();

        try (FileReader fileReader = new FileReader(configuracion.getPathJSONEmpleados())) {
            return Either.right(gson.fromJson(fileReader, userListType));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return Either.left(ConstantesDao.ERROR_AL_LEER_EL_ARCHIVO_JSON_DE_EMPLEADOS);
        }
    }

    @Override
    public boolean writeJSONEmpleados(List<Empleado> empleados) {

        try (FileWriter fileWriter = new FileWriter(configuracion.getPathJSONEmpleados())) {
            gson.toJson(empleados, fileWriter);
            return true;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Either<String, List<Franquicia>> readJSONFranquicias() {

        Type userListType = new TypeToken<List<Franquicia>>() {
        }.getType();

        try (FileReader fileReader = new FileReader(configuracion.getPathJSONFranquicias())) {
            return Either.right(gson.fromJson(fileReader, userListType));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return Either.left(ConstantesDao.ERROR_AL_LEER_EL_ARCHIVO_JSON_DE_FRANQUICIAS);
        }
    }

    @Override
    public boolean writeJSONFranquicias(List<Franquicia> franquicias) {

        try (FileWriter fileWriter = new FileWriter(configuracion.getPathJSONFranquicias())) {
            gson.toJson(franquicias, fileWriter);
            return true;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Either<String, List<Nomina>> readJSONNominas() {

        Type userListType = new TypeToken<List<Nomina>>() {
        }.getType();

        try (FileReader fileReader = new FileReader(configuracion.getPathJSONNominas())) {
            return Either.right(gson.fromJson(fileReader, userListType));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return Either.left(ConstantesDao.ERROR_AL_LEER_EL_ARCHIVO_JSON_DE_NOMINAS);
        }
    }

    @Override
    public boolean writeJSONNominas(List<Nomina> nominas) {

        try (FileWriter fileWriter = new FileWriter(configuracion.getPathJSONNominas())) {
            gson.toJson(nominas, fileWriter);
            return true;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Either<String, List<Registro>> readJSONRegistros() {
        Type userListType = new TypeToken<List<Registro>>() {
        }.getType();

        try (FileReader fileReader = new FileReader(configuracion.getPathJSONRegistros())) {
            return Either.right(gson.fromJson(fileReader, userListType));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return Either.left(ConstantesDao.ERROR_AL_LEER_EL_ARCHIVO_DE_REGISTROS);
        }
    }

    @Override
    public boolean writeJSONRegistros(List<Registro> registros) {

        try (FileWriter fileWriter = new FileWriter(configuracion.getPathJSONRegistros())) {
            gson.toJson(registros, fileWriter);
            return true;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }
}