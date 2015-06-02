/**
 * Copyright (c) 22/Feb/2015 Davide Cossu & Matthew Albrecht.
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 3 of the License, or (at your option) any
 * later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, see <http://www.gnu.org/licenses>.
 */

package com.minestellar.core.util;

import cpw.mods.fml.common.FMLLog;
import org.apache.logging.log4j.Level;

public class LogHelper {

    private  String n;

    public LogHelper(String modId){
        this.n = modId;
    }

    public void log(Level logLevel, Object object){
        FMLLog.log(n, logLevel, String.valueOf(object));
    }

    public void all(Object object){
        log(Level.ALL, object);
    }

    public void debug(Object object){
        log(Level.DEBUG, object);
    }

    public void error(Object object){
        log(Level.ERROR, object);
    }

    public void fatal(Object object){
        log(Level.FATAL, object);
    }

    public void info(Object object){
        log(Level.INFO, object);
    }

    public void off(Object object){
        log(Level.OFF, object);
    }

    public void trace(Object object){
        log(Level.TRACE, object);
    }

    public void warn(Object object){
        log(Level.WARN, object);
    }

}