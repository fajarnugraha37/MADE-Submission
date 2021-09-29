package com.example.githubapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListUserResponse(
    @field:SerializedName("total_count")
    val totalCount: Int,
    @field:SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @field:SerializedName("items")
    val items: List<UserResponse>
)

/** Followers/Following */
//[
//    {
//        login: "azisnaufal",
//        id: 2264036,
//        node_id: "MDQ6VXNlcjIyNjQwMzY=",
//        avatar_url: "https://avatars.githubusercontent.com/u/2264036?v=4",
//        gravatar_id: "",
//        url: "https://api.github.com/users/azisnaufal",
//        html_url: "https://github.com/azisnaufal",
//        followers_url: "https://api.github.com/users/azisnaufal/followers",
//        following_url: "https://api.github.com/users/azisnaufal/following{/other_user}",
//        gists_url: "https://api.github.com/users/azisnaufal/gists{/gist_id}",
//        starred_url: "https://api.github.com/users/azisnaufal/starred{/owner}{/repo}",
//        subscriptions_url: "https://api.github.com/users/azisnaufal/subscriptions",
//        organizations_url: "https://api.github.com/users/azisnaufal/orgs",
//        repos_url: "https://api.github.com/users/azisnaufal/repos",
//        events_url: "https://api.github.com/users/azisnaufal/events{/privacy}",
//        received_events_url: "https://api.github.com/users/azisnaufal/received_events",
//        type: "User",
//        site_admin: false
//    },
//]

/** DETAIL **/
//{
//    login: "satadii11",
//    id: 21214077,
//    node_id: "MDQ6VXNlcjIxMjE0MDc3",
//    avatar_url: "https://avatars.githubusercontent.com/u/21214077?v=4",
//    gravatar_id: "",
//    url: "https://api.github.com/users/satadii11",
//    html_url: "https://github.com/satadii11",
//    followers_url: "https://api.github.com/users/satadii11/followers",
//    following_url: "https://api.github.com/users/satadii11/following{/other_user}",
//    gists_url: "https://api.github.com/users/satadii11/gists{/gist_id}",
//    starred_url: "https://api.github.com/users/satadii11/starred{/owner}{/repo}",
//    subscriptions_url: "https://api.github.com/users/satadii11/subscriptions",
//    organizations_url: "https://api.github.com/users/satadii11/orgs",
//    repos_url: "https://api.github.com/users/satadii11/repos",
//    events_url: "https://api.github.com/users/satadii11/events{/privacy}",
//    received_events_url: "https://api.github.com/users/satadii11/received_events",
//    type: "User",
//    site_admin: false,
//    name: "Satria Adi Putra",
//    company: null,
//    blog: "https://medium.com/@satadii11",
//    location: "Bogor",
//    email: null,
//    hireable: null,
//    bio: "Hey you, yes you. Check my blog here https://medium.com/@satadii11",
//    twitter_username: "satadii11",
//    public_repos: 60,
//    public_gists: 72,
//    followers: 33,
//    following: 14,
//    created_at: "2016-08-24T06:47:19Z",
//    updated_at: "2021-09-23T11:05:57Z"
//}


/** Search */
//total_count: 1,
//incomplete_results: false,
//items: [
//{
//    login: "fajarnugraha37",
//    id: 25928059,
//    node_id: "MDQ6VXNlcjI1OTI4MDU5",
//    avatar_url: "https://avatars.githubusercontent.com/u/25928059?v=4",
//    gravatar_id: "",
//    url: "https://api.github.com/users/fajarnugraha37",
//    html_url: "https://github.com/fajarnugraha37",
//    followers_url: "https://api.github.com/users/fajarnugraha37/followers",
//    following_url: "https://api.github.com/users/fajarnugraha37/following{/other_user}",
//    gists_url: "https://api.github.com/users/fajarnugraha37/gists{/gist_id}",
//    starred_url: "https://api.github.com/users/fajarnugraha37/starred{/owner}{/repo}",
//    subscriptions_url: "https://api.github.com/users/fajarnugraha37/subscriptions",
//    organizations_url: "https://api.github.com/users/fajarnugraha37/orgs",
//    repos_url: "https://api.github.com/users/fajarnugraha37/repos",
//    events_url: "https://api.github.com/users/fajarnugraha37/events{/privacy}",
//    received_events_url: "https://api.github.com/users/fajarnugraha37/received_events",
//    type: "User",
//    site_admin: false,
//    score: 1
//}
//]