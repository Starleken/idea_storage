import { createOpenApiHttp } from "openapi-msw";
import { Config } from "@/shared/model/config";
import type { ApiPaths } from "../schema";

export const http = createOpenApiHttp<ApiPaths>({
  baseUrl: Config.API_BASE_URL,
});
