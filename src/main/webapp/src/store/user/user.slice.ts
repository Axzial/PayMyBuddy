import {ActionReducerMapBuilder, createAsyncThunk, createSlice, PayloadAction} from '@reduxjs/toolkit';
import {WebUser} from '@model/user/webuser.model';
import UserService from "@service/user.service";
import {NoInfer} from "@reduxjs/toolkit/dist/tsHelpers";
import AppConstants from "@config/AppConstants";
import {BaseState} from "@store/BaseState";

export interface UserState extends BaseState {
    user: WebUser | undefined;
}

export const INITIAL_USER_STATE: UserState = {
    user: undefined,
    loading: false,
    error: false,
}

export const fetchSelfUser = createAsyncThunk(
    "users/fetchSelfUser",
    async (jwt: string, thunkAPI) => {
        return UserService.fetchSelfUser(jwt)
            .then(res => res?.data)
            .catch(err => thunkAPI.rejectWithValue(err));
    }
)

export const userSlice = createSlice({
    name: 'user',
    initialState: INITIAL_USER_STATE,
    reducers: {
        setUser(state, action: PayloadAction<WebUser>) {
            state.user = action.payload;
        },
        clearUser(state, action: PayloadAction) {
            state.user = undefined;
        }
    },
    extraReducers: (builder: ActionReducerMapBuilder<NoInfer<UserState>>) => {
        builder.addCase(fetchSelfUser.pending, (state, action) => {
            return {...state, error: false, loading: true}
        })
        builder.addCase(fetchSelfUser.fulfilled, (state, action) => {
            return {user: action.payload, error: false, loading: false}
        })
        builder.addCase(fetchSelfUser.rejected, (state, action) => {
            return {...state, error: true, loading: false}
        })
    }
})
