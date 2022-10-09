import {ActionReducerMapBuilder, createAsyncThunk, createSlice} from "@reduxjs/toolkit";
import {WebUserPublic} from "@model/user/webuser-public.model";
import {v4} from "uuid";
import UserService from "@service/user.service";
import {NoInfer} from "@reduxjs/toolkit/dist/tsHelpers";

export interface ContactsState {
    contacts: WebUserPublic[]
}

export const INITIAL_CONTACTS_STATE: ContactsState = {
    contacts: []
}

export const fetchContacts = createAsyncThunk(
    "contacts/fetchContacts",
    async (jwt: string, thunkAPI) => {
        return UserService.fetchContacts(jwt)
            .then(res => res?.data.content)
            .catch(err => thunkAPI.rejectWithValue(err));
    }
)

export const contactsSlice = createSlice({
    name: "contacts",
    initialState: INITIAL_CONTACTS_STATE,
    reducers: {},
    extraReducers: (builder: ActionReducerMapBuilder<NoInfer<ContactsState>>) => {
        builder.addCase(fetchContacts.pending, (state, action) => {
            return {...state, error: false, loading: true}
        })
        builder.addCase(fetchContacts.fulfilled, (state, action) => {
            return {...state, contacts: action.payload, error: false, loading: false}
        })
        builder.addCase(fetchContacts.rejected, (state, action) => {
            return {...state, error: true, loading: false}
        })
    }
})
