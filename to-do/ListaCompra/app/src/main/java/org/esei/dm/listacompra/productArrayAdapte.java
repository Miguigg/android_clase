package org.esei.dm.listacompra;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import org.esei.dm.listacompra.mode.productos;

public class productArrayAdapte extends ArrayAdapter<productos> {

    public productArrayAdapte(@NonNull Context context, int resource) {
        super(context, resource);
    }
}
