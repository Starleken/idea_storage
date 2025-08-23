import FragmentList from "../../../components/list/fragment/FragmentList";

const data = [
    {
        id: 1,
        picture: "https://avatars.mds.yandex.net/i?id=b08c1e3a898aefb89fe2e3b0e52d4e00_l-10919913-images-thumbs&n=13",
        description: "Это локация 1"
    },
    {
        id: 2,
        picture: "https://avatars.mds.yandex.net/i?id=b08c1e3a898aefb89fe2e3b0e52d4e00_l-10919913-images-thumbs&n=13",
        description: "Это локация 2"
    },
    {
        id: 3,
        picture: "https://avatars.mds.yandex.net/i?id=b08c1e3a898aefb89fe2e3b0e52d4e00_l-10919913-images-thumbs&n=13",
        description: "Это локация 3"
    }
]

function FragmentPage() {
    return(
        <FragmentList list={data}/>
    )
}

export default FragmentPage;