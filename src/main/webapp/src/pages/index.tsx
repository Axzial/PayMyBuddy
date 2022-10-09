import type {NextPage} from 'next'
import React from 'react';
import {useRouter} from 'next/router';
import Base from "@component/layout/Base";

const Home: NextPage = () => {

    const router = useRouter();

    return (
        <Base>
            <div className={"h-screen w-screen bg-neutral-700"}>
            </div>
        </Base>
    )
}

export default Home
