import React, {useEffect} from 'react';
import {useRouter} from "next/router";
import AppConstants from "@config/AppConstants";
import {useAppDispatch} from "@pages/_app";
import {authSlice} from "@store/auth/auth.slice";

const Logout = () => {

    const dispatch = useAppDispatch();
    const router = useRouter();

    useEffect(() => {
        localStorage.removeItem(AppConstants.LOCAL_STORAGE_TOKEN_KEY)
        dispatch(authSlice.actions.setAuth(false));
        router.push("/login")
    })

    return (
        <div>

        </div>
    );
};

export default Logout;
