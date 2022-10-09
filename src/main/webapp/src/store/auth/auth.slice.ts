import {createAsyncThunk, createSlice, PayloadAction} from '@reduxjs/toolkit';
import AuthService, {LoginDTO, RegisterDTO} from "@service/auth.service";
import {BaseState} from "@store/BaseState";
import AppConstants from "@config/AppConstants";


export interface AuthState extends BaseState {
    isAuth: boolean;
}

export const INITIAL_AUTH_STATE: AuthState = {
    isAuth: false,
    loading: false,
    error: false
}

export const login = createAsyncThunk(
    "auth/login",
    async (dto: LoginDTO, thunkAPI) => {
        return AuthService.login(dto)
            .then(res => res?.data)
            .catch(err => thunkAPI.rejectWithValue(err));
    }
)

export const register = createAsyncThunk(
    "auth/register",
    async (dto: RegisterDTO, thunkAPI) => {
        return AuthService.register(dto)
            .then(res => res?.data)
            .catch(err => thunkAPI.rejectWithValue(err));
    }
)

export const authSlice = createSlice({
    name: 'auth',
    initialState: INITIAL_AUTH_STATE,
    reducers: {
        setAuth(state, action: PayloadAction<boolean>) {
            state.isAuth = action.payload;
        }
    },
    extraReducers: (builder) => {
        builder.addCase(login.pending, (state, action) => {
            state = {...state, error: false, loading: true}
        })
        builder.addCase(login.fulfilled, (state, action) => {
            state = {isAuth: true, error: false, loading: false}
            localStorage.setItem(AppConstants.LOCAL_STORAGE_TOKEN_KEY, <string>action.payload.token)
        })
        builder.addCase(login.rejected, (state, action) => {
            state = {...state, error: true, loading: false}
        })
        builder.addCase(register.pending, (state, action) => {
            state = {...state, error: false, loading: true}
        })
        builder.addCase(register.fulfilled, (state, action) => {
            state = {isAuth: true, error: false, loading: false}
            localStorage.setItem(AppConstants.LOCAL_STORAGE_TOKEN_KEY, <string>action.payload.token)
        })
        builder.addCase(register.rejected, (state, action) => {
            state = {...state, error: true, loading: false}
        })
    }
})
