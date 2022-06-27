package com.chenxuan.base.service.ipc;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public interface IPerson extends IInterface {
    public String eat(String food) throws RemoteException;

    public int age(int age) throws RemoteException;

    public String name(String name) throws RemoteException;

    public static abstract class Stub extends Binder implements IPerson {
        private static final String DESCRIPTOR = "com.chenxuan.base.service.ipc.IPerson.Stub";
        private static final int Transact_eat = 10050;
        private static final int Transact_age = 10051;
        private static final int Transact_name = 10052;

        public Stub() {
            this.attachInterface(this, DESCRIPTOR);
        }

        public static IPerson asInterface(IBinder iBinder) {
            if (iBinder == null) return null;
            IInterface iInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterface instanceof IPerson) {
                return (IPerson) iInterface;
            }
            return new Proxy(iBinder);
        }

        @Override
        public IBinder asBinder() {
            return this;
        }

        @Override
        protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case INTERFACE_TRANSACTION: {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                case Transact_eat: {
                    data.enforceInterface(DESCRIPTOR);
                    String _food = data.readString();
                    String _result = eat(_food);
                    reply.writeNoException();
                    reply.writeString(_result);
                    return true;
                }
                case Transact_age: {
                    data.enforceInterface(DESCRIPTOR);
                    int _age = data.readInt();
                    int _result = age(_age);
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                }
                case Transact_name: {
                    data.enforceInterface(DESCRIPTOR);
                    String name = data.readString();
                    String _result = name(name);
                    reply.writeNoException();
                    reply.writeString(_result);
                    return true;
                }
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        public static class Proxy implements IPerson {
            private final IBinder remote;

            public Proxy(IBinder remote) {
                this.remote = remote;
            }

            @Override
            public IBinder asBinder() {
                return remote;
            }

            @Override
            public String eat(String food) throws RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                String _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeString(food);
                    remote.transact(Stub.Transact_eat, _data, _reply, 0);
                    _reply.readException();
                    _result = _reply.readString();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }

            @Override
            public int age(int age) throws RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                int _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(age);
                    remote.transact(Stub.Transact_age, _data, _reply, 0);
                    _reply.readException();
                    _result = _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }

            @Override
            public String name(String name) throws RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                String _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeString(name);
                    remote.transact(Stub.Transact_name, _data, _reply, 0);
                    _reply.readException();
                    _result = _reply.readString();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }
        }
    }
}
