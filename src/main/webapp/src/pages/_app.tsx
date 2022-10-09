import React from 'react';
import '@styles/globals.css'
import type {AppProps} from 'next/app'
import {configureStore} from '@reduxjs/toolkit';
import {CookiesProvider} from 'react-cookie';
import {toast, ToastContainer} from 'react-toastify';
import {Provider, useDispatch} from 'react-redux';
import 'react-toastify/dist/ReactToastify.css';
import '@config/AxiosConfig';
import {authSlice} from '@store/auth/auth.slice';
import {userSlice} from '@store/user/user.slice';
import {transactionSlice} from '@store/transaction/transaction.reducer';
import {QueryClient, QueryClientProvider} from "react-query";
import {contactsSlice} from "@store/contacts/contacts.slice";
import "@config/AxiosConfig";

const store = configureStore({
    reducer: {
        authReducer: authSlice.reducer,
        userReducer: userSlice.reducer,
        transactionReducer: transactionSlice.reducer,
        contactsReducer: contactsSlice.reducer
    }
})
export type RootState = ReturnType<typeof store.getState>
export type AppDispatch = typeof store.dispatch;
export const useAppDispatch = () => useDispatch<AppDispatch>();

const queryClient = new QueryClient();

function PayMyBuddyApp({Component, pageProps}: AppProps) {

    return (
        <React.StrictMode>
            <QueryClientProvider client={queryClient}>
                <Provider store={store}>
                    <CookiesProvider>
                        <Component {...pageProps}/>
                        <ToastContainer
                            position={toast.POSITION.TOP_RIGHT}
                            autoClose={2000}
                            pauseOnFocusLoss={false}
                            icon={false}/>
                    </CookiesProvider>
                </Provider>
            </QueryClientProvider>
        </React.StrictMode>
    )
}

export default PayMyBuddyApp
