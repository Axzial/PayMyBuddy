import {useEffect} from 'react';
import {WebUser} from "@model/user/webuser.model";
import {useSelector} from "react-redux";
import {RootState, useAppDispatch} from "@pages/_app";
import {fetchSelfUser} from "@store/user/user.slice";
import {fetchTransactions} from "@store/transaction/transaction.reducer";

const useUser = (token?: string): WebUser | undefined => {

    const user = useSelector((state: RootState) => state.userReducer.user);
    const dispatch = useAppDispatch();
    console.log("Use User with ", token)

    useEffect(() => {
        if (!user && !!token) {
            dispatch(fetchSelfUser(token));
            dispatch(fetchTransactions(token))
        }
    }, [user, token]);

    return user;
};

export default useUser;
