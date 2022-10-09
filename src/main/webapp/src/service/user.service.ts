import axios, {AxiosResponse} from 'axios';
import Routes from '../config/ApiConfig';
import ApiConfig from '../config/ApiConfig';
import {WebUser} from '@model/user/webuser.model';
import {WebUserPublic} from "@model/user/webuser-public.model";
import PageDTO from "../utils/PageDTO";

class UserService {

    fetchSelfUser = (jwt: string): Promise<AxiosResponse<WebUser>> => {
        return axios.get<WebUser>(ApiConfig.BASE_URL + Routes.USERS_PATH,
            {headers: {'Authorization': `Bearer ${jwt}`}});
    }

    fetchContacts = (jwt: string): Promise<AxiosResponse<PageDTO<WebUserPublic>>> => {
        return axios.get<PageDTO<WebUserPublic>>(ApiConfig.BASE_URL + Routes.USERS_PATH + Routes.CONTACTS_PATH,
            {headers: {'Authorization': `Bearer ${jwt}`}});
    }

    searchContacts = (jwt: string, query: string): Promise<AxiosResponse<PageDTO<WebUserPublic>>> => {
        return axios.get<PageDTO<WebUserPublic>>(ApiConfig.BASE_URL + Routes.USERS_PATH + "/search?query=" + query,
            {headers: {'Authorization': `Bearer ${jwt}`}});
    }

    deleteContact = (jwt: string, email: string): Promise<AxiosResponse<WebUserPublic[]>> => {
        return axios.delete<WebUserPublic[]>(ApiConfig.BASE_URL + "/contacts", {headers: {'Authorization': `Bearer ${jwt}`}});
    }

    addContact = (jwt: string, email: string): Promise<AxiosResponse<WebUserPublic[]>> => {
        return axios.delete<WebUserPublic[]>(ApiConfig.BASE_URL + "/contacts", {headers: {'Authorization': `Bearer ${jwt}`}});
    }

}

export default new UserService();
