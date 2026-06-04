import dayjs from 'dayjs';

export interface ITabla {
  id?: string;
  campo01?: string | null;
  campo02?: number | null;
  campo03?: number | null;
  campo04?: number | null;
  campo05?: number | null;
  campo06?: number | null;
  campo07?: boolean | null;
  campo08?: dayjs.Dayjs | null;
  campo09?: dayjs.Dayjs | null;
  campo10?: dayjs.Dayjs | null;
  campo11?: string | null;
  campo12?: string | null;
  campo13?: string | null;
  campo14ContentType?: string | null;
  campo14?: string | null;
  campo15ContentType?: string | null;
  campo15?: string | null;
  campo16ContentType?: string | null;
  campo16?: string | null;
  campo17?: string | null;
}

export const defaultValue: Readonly<ITabla> = {
  campo07: false,
};
