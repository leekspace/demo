/*
 * Copyright (C) 2007-2010 Wayne Meissner
 *
 * This file is part of the JNR project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sengled.media.jffi.jnr.storage;

import jnr.ffi.LibraryLoader;
import jnr.ffi.LibraryOption;
import jnr.ffi.Pointer;
import jnr.ffi.provider.FFIProvider;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Map;

public final class LoadUtil {
    private LoadUtil() {}
    private static FFIProvider provider;

    public static void setProvider(FFIProvider provider) {
        LoadUtil.provider = provider;
    }
    
    public static interface HelperLib {
        Pointer ptr_from_buffer(ByteBuffer buf);
    }

    public static <T> T loadTestLib(String libname,Class<T> interfaceClass) {
        final Map<LibraryOption, ?> options = Collections.emptyMap();
        return loadTestLib(libname,interfaceClass, options);
    }
    public static <T> T loadTestLib(String libname,Class<T> interfaceClass, Map<LibraryOption, ?> options) {
        LibraryLoader<T> loader = (provider != null ? provider : FFIProvider.getSystemProvider()).createLibraryLoader(interfaceClass);

        loader.library(libname);
        for (Map.Entry<LibraryOption, ?> option : options.entrySet()) {
            loader.option(option.getKey(), option.getValue());
        }

        return loader.load();
    }

    public static Pointer getDirectBufferPointer(String libname,ByteBuffer buf) {
        return LoadUtil.loadTestLib(libname,HelperLib.class).ptr_from_buffer(buf);
    }
}
