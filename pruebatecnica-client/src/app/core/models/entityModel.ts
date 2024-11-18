export interface EntityModel<T> {
  data: T;
  links: { rel: string, href: string }[];
}
