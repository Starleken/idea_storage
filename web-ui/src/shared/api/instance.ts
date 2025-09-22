import { Config } from "../model/config";
import createFetchClient from "openapi-fetch";
import createClient from "openapi-react-query";
import type { ApiPaths } from "./schema";

export const fetchClient = createFetchClient<ApiPaths>({
  baseUrl: Config.API_BASE_URL,
});

export const rqClient = createClient(fetchClient);
