class ApiErrorModel {

    title?: string;
    status?: string;
    code?: number;
    timestamp?: string;
    meta?: Map<string, string>;

    constructor(data?: Partial<ApiErrorModel>) {
        Object.assign(this, data);
    }

}

export default ApiErrorModel;
