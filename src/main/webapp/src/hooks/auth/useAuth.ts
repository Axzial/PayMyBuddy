import {useRouter} from 'next/router';
import {RootState, useAppDispatch} from '@pages/_app';
import {useSelector} from 'react-redux';
import {authSlice} from '@store/auth/auth.slice';
import AppConstants from "@config/AppConstants";
import {useEffect} from "react";
import {useLocalStorage} from "react-use";


// TODO Broken
const useAuth = () => {

    const [token, set, remove] = useLocalStorage(AppConstants.LOCAL_STORAGE_TOKEN_KEY);
    const isAuth = useSelector((state: RootState) => state.authReducer.isAuth);
    const dispatch = useAppDispatch();
    const router = useRouter();

    const redirectLogin = () => {
        console.log("REDIRECT LOGIN")
    }

    const setStoreAuthState = (state: boolean) => {
        dispatch(authSlice.actions.setAuth(state));
    }

    useEffect(() => {
        if (!token) {
            setStoreAuthState(false);
            redirectLogin();
        }
    }, [token])

    return token as string;

};

export default useAuth;
