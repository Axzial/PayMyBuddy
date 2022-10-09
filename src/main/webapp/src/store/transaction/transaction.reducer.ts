import {ActionReducerMapBuilder, createAsyncThunk, createSlice, PayloadAction} from '@reduxjs/toolkit';
import {Transaction} from '@model/transaction/transaction.model';
import UserService from "@service/user.service";
import TransactionService from "@service/transaction.service";
import {NoInfer} from "@reduxjs/toolkit/dist/tsHelpers";
import {fetchSelfUser, UserState} from "@store/user/user.slice";
import {BaseState} from "@store/BaseState";


export interface TransactionState extends BaseState {
    transactions: Transaction[]
}

export const INITIAL_TRANSACTION_STATE: TransactionState = {
    transactions: [],
    error: false,
    loading: false,
}

export const fetchTransactions = createAsyncThunk(
    "transactions/fetchTransactions",
    async (jwt: string, thunkAPI) => {
        return TransactionService.getTransactions(jwt)
            .then(res => res?.data.content)
            .catch(err => thunkAPI.rejectWithValue(err));
    }
)

export const transactionSlice = createSlice({
    name: 'transaction',
    initialState: INITIAL_TRANSACTION_STATE,
    reducers: {
        addTransaction(state: TransactionState, action: PayloadAction<Transaction>) {
            console.log(action.payload)
            state.transactions.push(new Transaction(action.payload));
        },
        addTransactions(state: TransactionState, action: PayloadAction<Transaction[]>) {
            state.transactions = [...action.payload, ...state.transactions]
        }
    },
    extraReducers: (builder: ActionReducerMapBuilder<NoInfer<TransactionState>>) => {
        builder.addCase(fetchTransactions.pending, (state, action) => {
            return {...state, error: false, loading: true}
        })
        builder.addCase(fetchTransactions.fulfilled, (state, action) => {
            return {transactions: action.payload, error: false, loading: false}
        })
        builder.addCase(fetchTransactions.rejected, (state, action) => {
            return {...state, error: true, loading: false}
        })
    }
})
